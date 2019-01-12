package me.arun.arunrxjavaexploring;

import android.app.Application;

import me.arun.arunrxjavaexploring.RoomDB.localdb.AppDataBase;
import me.arun.arunrxjavaexploring.RoomDB.sharedpref.AppStorage;
import me.arun.arunrxjavaexploring.request.RetrofitClient;
import retrofit2.Retrofit;

public class RxJavaApplication extends Application
{

    public AppStorage appStorage;

    public AppStorage getAppStorage() {
        return appStorage;
    }

    public void setAppStorage(AppStorage appStorage) {
        this.appStorage = appStorage;
    }

    public AppDataBase getAppDataBase() {
        return appDataBase;
    }

    public void setAppDataBase(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    public AppDataBase appDataBase;

    public Retrofit getRetrofitClient() {
        return retrofitClient;
    }

    public void setRetrofitClient(Retrofit retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    public Retrofit retrofitClient;
    public Retrofit retrofitClient1;

    @Override
    public void onCreate() {
        super.onCreate();
        appStorage=new AppStorage(getApplicationContext(),"RoomDbSharedPref");
        appDataBase=AppDataBase.getAppDatabase(getApplicationContext());
        retrofitClient=RetrofitClient.getRetrofitInstance("https://v1.listado.co/v1/mobile/");
        retrofitClient1=RetrofitClient.getRetrofitInstance1();
    }


}
