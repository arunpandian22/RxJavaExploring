package me.arun.arunrxjavaexploring.RxOperaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Arrays;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.R;

public class TransFormingOperater extends AppCompatActivity {

    String TAG="TransFormingOperater";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_forming_operater);
    }


//    This operator periodically gather items from an Observable into bundles and emit these bundles rather than emitting the items one at a time
    public void bufferOperater(View view)
    {
        Observable.just("A", "B", "C", "D", "E", "F")
                .buffer(2)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        System.out.println("onNext(): ");
                        for (String s : strings) {
                            Log.d(TAG, "buffer  "+strings);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void mapOperater(View view){
        getOriginalObservable()
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(final Integer integer)  {
                        return (integer * 2);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "map onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    private Observable<Integer> getOriginalObservable() {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);

        return Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) {
                        for(Integer integer : integers) {

                            if (!emitter.isDisposed()) {
                                emitter.onNext(integer);
                            }
                        }

                        if(!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }

                });
    }



    public void flatMap(View view){
        /*
         * Here we are using flatMap() to multiply the resulting integer by 2
         * & emitting the result in another Observable.
         */
        getOriginalObservable() //123456
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer)  {
                        return getModifiedObservable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "flatMap onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





//    /*
//     * Here we are creating an Observable that iterates through the list of
//     * integers, and emits each integer.
//     */
//    private Observable<Integer> getOriginalFlatMapObservable() {
//        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
//
//        return Observable
//                .create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) {
//                        for(Integer integer : integers) {
//
//                            if (!emitter.isDisposed()) {
//                                emitter.onNext(integer);
//                            }
//                        }
//
//                        if(!emitter.isDisposed()) {
//                            emitter.onComplete();
//                        }
//                    }
//
//                });
//    }

    public void switchMap(View view)
    {
        getOriginalObservable()
                .switchMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer)  {
                        return getModifiedObservable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "switchMap onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public void conCatMapOperater(View view){
        getOriginalObservable()
                .concatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer)  {
                        return getModifiedObservable(integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "concatMap onNext: "+integer);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void groupBy(View view){
        /*
         * We will create an Observable with range of 1 to 10 numbers.
         * We use the groupBy operator to emit only even numbers from the list.
         * The output will be 2,4,6,8,10
         */
        Observable.range(1, 10)
                .groupBy(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return (integer % 2 == 0) ? true : false;
                    }
                })
                .subscribe(new Observer<GroupedObservable<Boolean, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                        if(booleanIntegerGroupedObservable.getKey()) {
                            booleanIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer integer) {
                                    Log.d(TAG, "groupBy onNext: "+integer);
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

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    
    public void scanOperater(View view)
    {
        Observable.range(1, 10)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return (integer + integer2);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, " scan onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    private Observable<Integer> getModifiedObservable(final Integer integer) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws InterruptedException {
                emitter.onNext((integer * 2));
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }
}
