package me.arun.arunrxjavaexploring.utils.FragmentHelper;

/**
 * Created by Jaison.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;


public abstract class BackStackFragment1 extends Fragment
{


    String TAG="BackStackFragment1";

    public static boolean handleBackPressed(FragmentManager fm)
    {
        if(fm.getFragments() != null)
        {
            for(Fragment frag : fm.getFragments())
            {
                if(frag != null && frag.isVisible() && frag instanceof BackStackFragment1)
                {
                    if(((BackStackFragment1)frag).onBackPressed())
                    {
                        Log.d("BackStackFragment", "handleBackPressed: ");
                        return true;
                    }
                }
            }
        }
        return false;
    }



    protected boolean onBackPressed()
    {

        FragmentManager fm = getChildFragmentManager();
        if(handleBackPressed(fm)){
            Log.d("BackStackFragment", "onBackPressed(fm): ");
            return true;
        } else if(getUserVisibleHint() && fm.getBackStackEntryCount() > 0){
            fm.popBackStack();
            Log.d("BackStackFragment", "popBackStack(fm): ");
            return true;
        }
        return false;
    }
//
//    private onActionBarClickListener actionBarClickListener=new CustomActionBar.onActionBarClickListener() {
//        @Override
//        public void onHomeClick() {
//            Log.d(TAG, "onHomeClick: ");
//            onHomeClicked();
//        }
//
//        @Override
//        public void onOverflowClick() {
//            Log.d(TAG, "onOverflowClick: ");
//            onOverflowClicked();
//        }
//    };
//
//    public onActionBarClickListener getActionBarListener()
//    {
//        return actionBarClickListener;
//    }

    public void onHomeClicked()
    {

    }

     public void onOverflowClicked()
    {

    }

}
