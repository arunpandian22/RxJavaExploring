package me.arun.arunrxjavaexploring.RoomDB.sharedpref;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * A class for create the shared preferences and and store the value in that
 */
public class AppStorage {

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    Context context;

    public AppStorage(Context context, String name)
    {
        this.context = context;
        this.appSharedPrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    /**
     * A method to get the value from shared prefernces
     * @param keyname a keyname of the shared prefernce
     * @return return the value for the appropriate shared preference keyname
     */
    public String getStringDetails(String keyname)
    {
        return appSharedPrefs.getString(keyname,"");

    }

    public Long getLongDetails(String keyname)
    {
        return appSharedPrefs.getLong(keyname,0);

    }
    public void SetLongDetails(String keyname, long value)
    {
        prefsEditor.putLong(keyname,value);
        prefsEditor.commit();
    }

    /**
     * A method to set the value in shared prefernce
     * @param keyname keyname of shared preference
     * @param value  value of the shared prefernce
     */
    public void setStringDetails(String keyname, String value)
    {
        prefsEditor.putString(keyname, value);
        prefsEditor.commit();
    }

    public void setIntegerDetails(String keyname,int value)
    {
        prefsEditor.putInt(keyname,value);
        prefsEditor.commit();
    }

    public Integer getIntegerDetails(String keyname)
    {
        return appSharedPrefs.getInt(keyname,0);

    }

    public Boolean getBooleanDetails(String keyname)
    {
        return appSharedPrefs.getBoolean(keyname,false);
    }

    public void setBooleanDetails(String keyname, Boolean value)
    {
        prefsEditor.putBoolean(keyname, value);
        prefsEditor.commit();
    }

    public void clear()
    {
        prefsEditor.clear().apply();
    }

}

