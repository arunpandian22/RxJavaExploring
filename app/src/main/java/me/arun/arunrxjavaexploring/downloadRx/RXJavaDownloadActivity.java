package me.arun.arunrxjavaexploring.downloadRx;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.RxJavaApplication;
import me.arun.arunrxjavaexploring.request.InterfaceService;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RXJavaDownloadActivity extends AppCompatActivity {

    @BindView(R.id.ivDl)
    ImageView ivDl;
    InterfaceService interfaceService;
    Retrofit retrofit;
    String TAG="RXJavaDownloadActivity";
    CompositeDisposable compositeDisposable=new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_download);
        ButterKnife.bind(this);
        retrofit=((RxJavaApplication)getApplication()).retrofitClient;

        interfaceService=retrofit.create(InterfaceService.class);



        compositeDisposable.add(interfaceService.fetchCaptcha("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg").observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Response<Bitmap>>(){


            @Override
            public void onSuccess(Response<Bitmap> bitmapResponse) {
             ivDl.setImageBitmap(bitmapResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }
        }));

    }
}
