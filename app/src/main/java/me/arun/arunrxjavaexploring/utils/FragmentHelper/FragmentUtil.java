package me.arun.arunrxjavaexploring.utils.FragmentHelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * A class created for the use the Util functions related to the Fragments
 * Created by Jaison.
 */
public class FragmentUtil
{
    /**
     * A method to redirect the given fragment in current fragmentManager without tag
     *
     * @param redirectFragment a param has the value of the redirected fragment
     * @param manager          a param has the fragment manager
     * @param containerID      a param has the id of the View
     */
    public static void fragmentRedirection(Fragment redirectFragment, FragmentManager manager, int containerID) {
        fragmentRedirection(redirectFragment,manager,containerID, null);
    }

    /**
     * A method to redirect the given fragment in current fragmentManager without tag
     * @param redirectFragment  a param has the value of the redirected fragment
     * @param manager a param has the fragment manager
     * @param containerID a param has the id of the View
     * @param tag a param has the value of the tag which has to add in fragment
     */
    public static void fragmentRedirection(Fragment redirectFragment, FragmentManager manager, int containerID, String tag) {
        Log.d("fragmentRedirection", "fragmentRedirection: "+tag);
        //manager.popBackStack(tag, 0);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(containerID, redirectFragment,tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


}
