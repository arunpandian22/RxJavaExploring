package me.arun.arunrxjavaexploring.RxOperaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import me.arun.arunrxjavaexploring.R;


public class GroupBy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_by);

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
                                    System.out.println("onNext: " + integer);
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


}
