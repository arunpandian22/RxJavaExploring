package me.arun.arunrxjavaexploring.RxOperaters;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import io.reactivex.subjects.Subject;
import me.arun.arunrxjavaexploring.R;

public class UtilityOperater extends AppCompatActivity
{
 String TAG="UtilityOperater";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_operater);
    }

    public void delayOperater(View view)
    {
        Observable.just("A", "B", "C", "D", "E", "F")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: "+s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void doOnEach(View view)
    {
//        /* Example of doOnNext()  */
//        Observable.just("A", "B", "C", "D", "E", "F")
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) {
//                        Log.d(TAG, "doOnEach accept: "+s);
//                    }
//                })
//                .subscribe();

        /* Example of doOnSubscribe(), doOnUnSubscribe(), doOnEach()  */
        Observable.range(1, 5)
                .doOnEach(new Observer<Integer>() {

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Complete is called");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribe is called");
                    }

                    @Override
                    public void onNext(Integer value) {
                        System.out.println("onNext: " + value);
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        System.out.println("onSubscribe is called");
                    }
                })
                .subscribe();
    }

    public void materialize(View view)
    {
        Observable.just("A", "B", "C", "D", "E", "F")
                .materialize()
                .subscribe(new Observer<Notification<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Notification<String> stringNotification) {
                        /*
                         * From the notification object, we can check if the
                         * emitted item is:
                         * isOnNext() or isOnError() or isOnComplete()
                         *
                         * Here we can basically fetch items that are successful
                         * & omit items that resulted in error.
                         */



                        Log.d(TAG, "onNext: "+stringNotification.getValue());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void observeOn(View view){
        Observable.just("A", "BB", "CCC", "DDDD", "EEEEE", "FFFFFF") // UI
                .map(str -> str.length()) // UI
                .observeOn(Schedulers.computation()) //Changing the thread
                .map(length -> 2 * length)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: 2 * length of string:"+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public void subscribeOn(View view){
        Observable.range(1, 5)
                .map(i -> i * 100)
                .doOnNext(i -> {

                    Log.d(TAG, "subscribeOn: Emitting  " + i + " on thread " + Thread.currentThread().getName());
                })
                .subscribeOn(Schedulers.computation())
                .map(i -> i * 10)
                .subscribe(i -> {
                    Log.d(TAG, "subscribeOn: received" + i + " on thread " + Thread.currentThread().getName());
                });
    }

    public void timeInterVal(View view)
    {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .timeInterval()
                .subscribe(new Subject<Timed<Long>>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super Timed<Long>> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<Long> longTimed) {
                        Log.d(TAG, "onNext: "+longTimed);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void timeOutOperater(View view){
        Observable.just(1l, 2l, 3l, 4l, 5l, 6l)
                .timer(1, TimeUnit.SECONDS)
                .timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                        Log.d(TAG, "onNext: "+aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void timeStampOperater(View view)
    {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .timestamp()
                .subscribe(new Observer<Timed<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<Long> longTimed) {
                        Log.d(TAG, "onNext: timeStamedOperater"+longTimed);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void usingOperater(View view){
        Observable.using(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "Example";
                    }
                },

                new Function<String, ObservableSource<Character>>() {
                    @Override
                    public ObservableSource<Character> apply(String s) {
                        return Observable.create(o -> {
                            for (Character c : s.toCharArray()) {
                                o.onNext(c);
                            }
                            o.onComplete();
                        });
                    }
                },

                new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        Log.d(TAG, "accept: "+s);
                    }
                }
        )

                .subscribe(new Observer<Character>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Character character) {
                        Log.d(TAG, "onNext: "+character);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
