package me.arun.arunrxjavaexploring.utils;

import android.content.Context;
import android.provider.Settings;

public class CommonUtills {

    public static String getDeviceId(Context context){
         String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
         return android_id;
    }
}
