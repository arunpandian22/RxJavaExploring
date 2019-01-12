package me.arun.arunrxjavaexploring.utils.FragmentHelper;

/**
 * A Class created for the get the view id for the Fragment add
 * Created by Jaison.
 */
public class FrgamentViewSelectionHelper
{
    public static final String TAG="ViewSelectionHelper";
    public static final int LOCATION_COLLECTION_DETAILS=1;
    public static final int FAV_COLLECTION_DETAILS=2;
    public static final int COLLECTION_PLACE_LIST=3;


    /**
     * A method to get the View Id based on HomeSource enum type
     *
     * @param pageSource a param has the Enum type of HomeSource
     * @return it returns the id of the view
     */
//    public static int getParentSourceId(HomeSource pageSource) {
//        switch (pageSource) {
//            case HOME:
//                return R.id.navigationHome;
//            case SEARCH:
//                return R.id.navigationSearch;
//            case SETTINGS:
//                return R.id.navigationSettings;
//            case FAVOURITE:
//                return R.id.navigationFavourites;
//            default:
//                return 0;
//        }
//    }


    /**
     * A method to get the View Id based on pageSource
     * @param pageSource a param has the value for which screen layout has to select
     * @return it returns the view id for the particular screen based on given pageSource value
     */
//    public static int getCollectionViewId(int pageSource) {
//        Log.d(TAG, "getCollectionFrameId : "+pageSource);
//
//        switch(pageSource) {
//            case LOCATION_COLLECTION_DETAILS:
//                return R.layout.fragment_home;
//            case FAV_COLLECTION_DETAILS:
//                return R.layout.fragment_place_details;
//            case COLLECTION_PLACE_LIST:
//                return R.layout.fragment_place_listing;
//            default:
//                return R.layout.fragment_home;
//        }
//    }




    /**
     *  A enum Class created to Bottom Tabs type
     */
    public enum HomeSource {
        HOME, SEARCH, FAVOURITE, SETTINGS
    }



}
