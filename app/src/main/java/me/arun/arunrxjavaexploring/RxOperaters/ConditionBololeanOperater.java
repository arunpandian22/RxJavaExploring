package me.arun.arunrxjavaexploring.RxOperaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import me.arun.arunrxjavaexploring.R;
public class ConditionBololeanOperater extends AppCompatActivity
{

    String  TAG="ConditionBololeanOperater";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_bololean_operater);
    }

    public void allOperater(View view) {

        Observable.just(0, 1, 2, 3, 4, 0, 6, 0,-1)
                .all(item -> item > 0)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Log.d(TAG, "all: onSuccess: "+aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "all onError: ");
                    }
                });
    }

    public void ampOperater(View view)
    {
        Observable<Integer> observable1 = Observable.timer(4, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Long aLong) throws Exception {
                        return Observable.just(10, 20, 30, 40, 50);
                    }
                });

        Observable<Integer> observable2 = Observable.timer(6, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Long aLong) throws Exception {
                        return Observable.just(100, 200, 300, 400, 500);
                    }
                });

        Observable.ambArray(observable1, observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: ambArray"+integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "ambArray onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ambArray");
                    }
                });
    }

    public void containsOperater(View view){

        Observable.just(1, 2, 3, 4, 5, 6)
                .contains(10)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Log.d(TAG, "contains onSuccess: 10 is in this list "+aBoolean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }
                });
    }

    public void defaultEmptyOperater(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                int num = (int) (Math.random() * 10);
                if (num % 2 == 0) {
                    emitter.onNext(num);
                }
                emitter.onComplete();
            }
        })
                .defaultIfEmpty(-10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                        Log.d(TAG, "defaultIfEmpty onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void sequentialOperater(View view){
        Observable<Integer> observable1 = Observable
                .just(1, 2, 3, 4, 5, 6);

        Observable<Integer> observable2 = Observable
                .just(1, 2, 3, 4, 5, 6);

        Observable.sequenceEqual(observable1, observable2)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Log.d(TAG, "onSuccess: Are the two sequences equal:"  + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }
                });
    }

    public void skipUntilOperater(View view){

        Observable<Integer> observable1 = Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                });

        Observable<Integer> observable2 = Observable
                .timer(3, TimeUnit.SECONDS)
                .flatMap(__ -> Observable.just(11, 12, 13, 14, 15, 16));

        observable1.skipUntil(observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: skipUntilOperater "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void skipWhile(View view){
        Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })
                .skipWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        return (((Integer)o < 2));
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "onNext: "+o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public void takeUntilOperater(View view){
        Observable<Integer> observable1 = Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                });

        Observable<Integer> observable2 = Observable
                .timer(3, TimeUnit.SECONDS)
                .flatMap(__ -> Observable.just(11, 12, 13, 14, 15, 16));

        observable1.takeUntil(observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void takeWhile(View view)
    {
        Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })
                .takeWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) {
                        return (((Integer)o < 2));
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
