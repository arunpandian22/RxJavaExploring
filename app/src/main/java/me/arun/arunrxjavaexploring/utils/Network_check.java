package me.arun.arunrxjavaexploring.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network_check
{
    /**
     * Created by Arun Pandian M on 21/December/2018
     * arunsachin222@gmail.com
     * Chennai
     */

    public static boolean isNetworkAvailable(Context context)
    {
        boolean networkavailable = false;

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                networkavailable = true;
            } else {
                networkavailable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return networkavailable;
    }

}