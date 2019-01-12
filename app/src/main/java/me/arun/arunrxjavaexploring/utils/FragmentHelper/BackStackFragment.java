package me.arun.arunrxjavaexploring.utils.FragmentHelper;
/**
 * Created by Jaison.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;

import me.arun.arunrxjavaexploring.utils.LocationHelper.PermissionUtils;


/**
 * A class created to maintain the Stack of Fragments and handle the backpressed by os
 */
public abstract class BackStackFragment extends Fragment implements /*NetworkResultListener,*/ ActivityCompat.OnRequestPermissionsResultCallback {

    // Check Permissions

    PermissionUtils permissionUtils;
    ArrayList<String> permissions = new ArrayList<>();
    int permissionRequestCode;
    String TAG = "BackStackFragment";

//    /**
//     * a method to initalize the ActionBarClickListener
//     */
//    private onActionBarClickListener actionBarClickListener = new CustomActionBar.onActionBarClickListener() {
//        @Override
//        public void onHomeClick() {
//            Log.d(TAG, "onHomeClick: actionbar");
//            onHomeClicked();
//            hardRefresh();
//        }
//
//        @Override
//        public void onOverflowClick() {
//            Log.d(TAG, "onOverflowClick: ");
//            onOverflowClicked();
//        }
//    };

    /**
     * a method to initalize the OnPermissionRequestListener
     */
    private PermissionUtils.OnPermissionRequestListener onPermissionRequestListener = new PermissionUtils.OnPermissionRequestListener() {
        @Override
        public void PermissionGranted(int request_code) {
            Log.d(TAG, "PermissionGranted: ");
            onPermissionGranted(request_code);
        }

        @Override
        public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
            onPartialPermissionGranted(request_code, granted_permissions);
        }

        @Override
        public void PermissionDenied(int request_code) {

        }

        @Override
        public void NeverAskAgain(int request_code) {

        }
    };

    /**
     * a  method to handle the back pressed
     *
     * @param fm a Fragmentmanager of the Fragment
     * @return it returns true if fragment has the child fragment
     */
    public static boolean handleActivityBackPressed(FragmentManager fm) {
        if (fm.getFragments() != null) {
            for (Fragment frag : fm.getFragments()) {
                if (frag != null && frag.isVisible() && frag instanceof BackStackFragment) {
                    if (((BackStackFragment) frag).onBackPressed()) {
                        Log.d("BackStackFragment", "handleBackPressed: " + frag.getTag());
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * a  method to handle the back pressed
     * @param fm a Fragmentmanager of the Fragment
     * @return it returns true if fragment has the child fragment
     */
    public boolean handleBackPressed(FragmentManager fm) {
        if (fm.getFragments() != null) {
            for (Fragment frag : fm.getFragments()) {
                hardRefresh();
                if (frag != null && frag.isVisible() && frag instanceof BackStackFragment) {
                    if (((BackStackFragment) frag).onBackPressed()) {
                        Log.d("BackStackFragment", "handleBackPressed:  " + frag.getClass().getSimpleName());

                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void checkMyFragment() {
        if (getFragmentManager().getFragments() != null) {
            for (Fragment frag : getFragmentManager().getFragments()) {
                if (frag != null && frag.isVisible() && frag instanceof BackStackFragment) {
                    Log.d(TAG, "checkMyFragment: tags " + frag.getTag());
                }
            }
        }
    }

    /**
     * A method to initialize the PermissionUtils
     * @param context a context of the current activity
     */
    public void init(Context context) {
        // To get the needed permission from the user
        Log.d(TAG, "init: ");
        permissionUtils = new PermissionUtils(context, getOnPermissionRequestListener(), this);
    }

    /**
     * a method to popstack the child fragment if the fragment has child and do the hard refresh
     * @return it return true if the child fragmnet is available
     */
    public boolean onBackPressed() {
        hardRefresh();
        FragmentManager fm = getChildFragmentManager();
        if (handleBackPressed(fm)) {
            Log.d("BackStackFragment", "onBackPressed(fm): ");
            return true;
        } else if (getUserVisibleHint() && fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            Log.d("BackStackFragment", "popBackStack(fm): ");
            return true;
        }
        return false;
    }

    /**
     * a method to get the actionbar click listener
     * @return it returns the actionbar click listener
     */
//    public onActionBarClickListener getActionBarListener() {
//        return actionBarClickListener;
//    }
//
//    public void onHomeClicked() {
//        Log.d(TAG, "onHomeClicked: methos ");
//        if (getActivity() != null) {
//            getActivity().onBackPressed();
//        }
//    }
//
//    public void onOverflowClicked() {
//
//    }
//
//    @Override
//    public void onCollectionListingSuccess(in.nfnlabs.listado.Model.collection.CollectionListModel collectionListModel) {
//        Log.d(TAG, "onCollectionListingSuccess: ");
//    }
//
//    @Override
//    public void onPlacesListingSuccess(PlaceListModel placeListModel) {
//    }
//
//    @Override
//    public void onPaymentSuccess(PurchaseStatus purchasesResult) {
//        Log.d(TAG, "onPaymentSuccess: ");
//    }
//
//    @Override
//    public void onPurchaseRevertSuccess(PurchaseRevert purchaseRevert) {
//
//    }
//
//    @Override
//    public void onAboutAuthorSuceess(AuthorModel authorModel) {
//
//    }
//
//    @Override
//    public void onGetTagsSucces(TagsModel tagsModel) {
//
//    }
//
//    @Override
//    public void onAboutAppSuceess(App authorModel) {
//
//    }
//
//    @Override
//    public void onNetworkFailure(int reqID) {
//        Log.d(TAG, "onNetworkFailure: ");
//    }
//
//    @Override
//    public void onAPIError(int reqID) {
//        Log.d(TAG, "onAPIError: ");
//    }
//
//    @Override
//    public void onFilterViewSuccess(FilterViewTypeModel filterViewTypeModel) {
//
//    }
//
//    @Override
//    public void onPlacesDetailsSuccess(PlaceListingDetails placeListingDetails) {
//
//    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        if (visible) onTrueResume();
        super.setUserVisibleHint(visible);
    }

    public void hardRefresh() {
        Log.d(TAG, "hardRefresh: backstackfragment" + getUserVisibleHint());
        if (getUserVisibleHint()) {
            onTrueResume();
        }
    }

    /**
     * a method to override the onResume
     */
    public void onTrueResume() {
        Log.d(TAG, "onTrueResume: ");
    }

    // To check the needed permission

    /**
     * a method to check the needed permission
     * @param permissions a list of permissions
     * @param permissionExplanation a text to why that permission is needed
     * @param requestCode a request code of the permission
     */
    public void doPermissionCheck(ArrayList<String> permissions, String permissionExplanation, int requestCode) {

        if (permissions.size() > 0) {
            Log.d(TAG, "doPermissionCheck: " + permissions.size());
            this.permissions.clear();
            this.permissions.addAll(permissions);
            permissionRequestCode = requestCode;

            if (permissionUtils != null) {
                Log.d(TAG, "doPermissionCheck: check permission");
                permissionUtils.checkPermission(this.permissions, permissionExplanation, requestCode);
            } else
                Log.d(TAG, "doPermissionCheck: ");
        }
    }

    /**
     * a method to get the OnPermissionRequestListener
     * @return it returns the OnPermissionRequestListener instance
     */
    public PermissionUtils.OnPermissionRequestListener getOnPermissionRequestListener() {
        return onPermissionRequestListener;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // redirects to utils
        Log.d(TAG, "onRequestPermissionsResult: " + requestCode);
        Log.d(TAG, "onRequestPermissionsResult: permission" + permissionRequestCode);
        if (requestCode == permissionRequestCode)
            permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);

        onFragmentPermissionsResult(requestCode, permissions, grantResults);

    }

    /**
     * a method returns the result of permissions
     * @param requestCode a request code of the permission
     * @param permissions a list of permissions
     * @param grantResults a result of permissions
     */
    public void onFragmentPermissionsResult(int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults) {

    }

    /**
     * a method will be called if all the permiision are granted
     * @param requestCode a request code of the permisions
     */
    public void onPermissionGranted(int requestCode) {
        Log.d(TAG, "onPermissionGranted: ");
    }

    /**
     * a method will be called when the partially permissions are gave by user
     * @param requestCode a requset code of the permissions
     * @param grantedPermissions a list of granted permissions
     */
    public void onPartialPermissionGranted(int requestCode, ArrayList<String> grantedPermissions) {
        Log.d(TAG, "onPartialPermissionGranted: ");
    }

}
