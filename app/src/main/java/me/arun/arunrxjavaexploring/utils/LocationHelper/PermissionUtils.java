package me.arun.arunrxjavaexploring.utils.LocationHelper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jaison on 25/08/16.
 */


public class PermissionUtils
{

    String TAG="PermissionUtils";
    Context context;
    Activity current_activity;
    Fragment currentFragment;

    OnPermissionRequestListener onPermissionRequestListener;


    ArrayList<String> permission_list=new ArrayList<>();
    ArrayList<String> listPermissionsNeeded=new ArrayList<>();
    String dialog_content="";
    int req_code;

    public PermissionUtils(Context context)
    {
        this.context=context;
        this.current_activity= (Activity) context;

        onPermissionRequestListener= (OnPermissionRequestListener) context;
    }

    public PermissionUtils(Context context, OnPermissionRequestListener callback)
    {
        this.context=context;
        this.current_activity= (Activity) context;

        onPermissionRequestListener= callback;
    }

    public PermissionUtils(Context context, OnPermissionRequestListener callback, Fragment currentFragment)
    {
        this.context=context;
        this.current_activity= (Activity) context;
        this.currentFragment=currentFragment;
        onPermissionRequestListener= callback;
    }

    /**
     * a method to Check the API Level & Permission
     *
     * @param permissions a param has the value of the list of permissions to check
     * @param dialog_content a param has the dialog content to show if the user has to request to grant permission
     * @param request_code a param has the request code of the permission
     */

    public void checkPermission(ArrayList<String> permissions, String dialog_content, int request_code)
    {
        this.permission_list=permissions;
        this.dialog_content=dialog_content;
        this.req_code=request_code;

        Log.d(TAG, "checkPermission: ");

       if(Build.VERSION.SDK_INT >= 23)
       {
           if (checkAndRequestPermissions(permissions, request_code))
           {
               onPermissionRequestListener.PermissionGranted(request_code);
               Log.i(TAG,"all permissions granted");
               Log.i(TAG,"proceed to callback");
           }
       }
        else
       {
           onPermissionRequestListener.PermissionGranted(request_code);

           Log.i(TAG,"all permissions granted");
           Log.i(TAG,"proceed to callback");
       }

    }


    /**
     * a method to  Check and request the Permissions
     *
     * @param permissions a param has the value of the list of permissions to check
     * @param request_code a param has the request code of the permission
     * @return it returns the true if the permission are granted
     */

    private  boolean checkAndRequestPermissions(ArrayList<String> permissions, int request_code) {

        if(permissions.size()>0)
        {
            listPermissionsNeeded = new ArrayList<>();

            for(int i=0;i<permissions.size();i++)
            {
                int hasPermission = ContextCompat.checkSelfPermission(current_activity,permissions.get(i));

                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permissions.get(i));
                }

            }

            if (!listPermissionsNeeded.isEmpty())
            {
                if(currentFragment!=null)
                    currentFragment.requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),request_code);
                else
                    ActivityCompat.requestPermissions(current_activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),request_code);
                return false;
            }
            else
                onPermissionRequestListener.PermissionGranted(req_code);
        }

        return true;
    }

    /**
     *A method to call the call back methods based on the permission granted
     * @param requestCode  param has the request code of the permission
     * @param permissions  a param has the value of the list of permissions to check
     * @param grantResults a param has the array results of the permissions
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        if(requestCode==req_code)
        {
            if(grantResults.length>0)
            {
                Map<String, Integer> perms = new HashMap<>();

                for (int i = 0; i < permissions.length; i++)
                {
                    perms.put(permissions[i], grantResults[i]);
                }

                final ArrayList<String> pending_permissions=new ArrayList<>();

                for (int i = 0; i < listPermissionsNeeded.size(); i++)
                {
                    if (perms.get(listPermissionsNeeded.get(i)) != PackageManager.PERMISSION_GRANTED)
                    {
                        if(ActivityCompat.shouldShowRequestPermissionRationale(current_activity,listPermissionsNeeded.get(i)))
                            pending_permissions.add(listPermissionsNeeded.get(i));
                        else
                        {
                            Log.i("Go to settings","and enable permissions");
                            onPermissionRequestListener.NeverAskAgain(req_code);
                            Toast.makeText(current_activity, "Go to settings and enable permissions", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                }

                if(pending_permissions.size()>0)
                {
                    showMessageOKCancel(dialog_content,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            checkPermission(permission_list,dialog_content,req_code);
                                            break;
                                        case DialogInterface.BUTTON_NEGATIVE:
                                            Log.i("permisson","not fully given");
                                            if(permission_list.size()==pending_permissions.size())
                                                onPermissionRequestListener.PermissionDenied(req_code);
                                            else
                                                onPermissionRequestListener.PartialPermissionGranted(req_code,pending_permissions);
                                            break;
                                    }


                                }
                            });

                }
                else
                {
                    Log.i("all","permissions granted");
                    Log.i("proceed","to next step");
                    onPermissionRequestListener.PermissionGranted(req_code);

                }



            }
        }
    }


    /**
     * a method to Explain why the app needs permissions
     *
     * @param message a param has the message of the permission needed descriptions
     * @param okListener a param has the listener of the DialogInterface.OnClickListener
     */
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(current_activity)
                .setMessage(message)
                .setPositiveButton("Ok", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    /**
     * A interface has the abstract the methods to handle the Permission request listener
     */
    public interface OnPermissionRequestListener
    {
        void PermissionGranted(int request_code);
        void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions);
        void PermissionDenied(int request_code);
        void NeverAskAgain(int request_code);
    }

}
