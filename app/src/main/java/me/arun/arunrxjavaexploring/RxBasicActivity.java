package me.arun.arunrxjavaexploring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.anotation.CustomOverride;
public class RxBasicActivity extends AppCompatActivity
{
    String TAG="RxBasicActivity";
    public Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * observable using just operater
         *The Just operator converts an item into an Observable that emits that item.
         * @see (http://reactivex.io/documentation/operators/just.html)
         */
        Observable<String> androidObservable = Observable.just("Arun", "Bala", "Chandru", "Jaison", "Ranjith");

        Observer<String> observerDeveloperName=getDeveperObserver();

        androidObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observerDeveloperName);

        // operaters added to filter the data
        androidObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .subscribe(observerDeveloperName);
    }

    private Observer<String> getDeveperObserver()
    {
        return new Observer<String>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                Log.d(TAG, "onSubscribe");
                disposable=d;
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        //if activity destroyed events won't send
        disposable.dispose();
    }
}
