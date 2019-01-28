package me.arun.arunrxjavaexploring.RxOperaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import me.arun.arunrxjavaexploring.R;

public class FilteringActivity extends AppCompatActivity {
String TAG="FilteringActivity ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

    }


    public void distinctOperater(View view){

        Observable.just(10, 20, 20, 10, 30, 40, 70, 60, 70)
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("distinct onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void elementAtOperater(View view) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .elementAt(1)
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        System.out.println("elementAt onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void filterOperater(View view)
    {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer % 2 == 0);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("filter onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void ignoreElements(View view){
        Observable.range(1, 10)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribed");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("ingnoreElements Completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void sampleOperater(View view) {

        Observable timedObservable = Observable
                .just(1, 2, 3, 4, 5, 6,8,10,12,14)
                .zipWith(Observable.interval(
                        0, 1, TimeUnit.SECONDS), (item, time) -> item);

        timedObservable
                .sample(4, TimeUnit.SECONDS)
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "sample onNext: "+o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void skipOperater(View view) throws InterruptedException {

        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .skip(4)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "skipOperator onNext: "+s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



        public void skipLastOperater(View view) throws InterruptedException
        {
            Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                    .skipLast(4)
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {
                            Log.d(TAG, "skiplast onNext: "+s);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        public void takeOperater(View view)
        {

            Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                    .take(4)
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {
                            Log.d(TAG, "take onNext: "+s);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }


    public void takeLastOberator(View view)
    {

        Observable.just("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
                .takeLast(4)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "takelast onNext: "+s);
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
