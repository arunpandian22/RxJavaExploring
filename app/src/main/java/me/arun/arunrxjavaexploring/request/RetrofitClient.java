package me.arun.arunrxjavaexploring.request;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit,retrofit1;

    public static Retrofit getRetrofitInstance(String baseUrl)
    {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstance1()
    {
        if (retrofit1 == null)
        {
            retrofit1 = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://v1.listado.co/v1/mobile/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }
}
