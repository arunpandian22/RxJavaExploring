package me.arun.arunrxjavaexploring.RxOperaters;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import me.arun.arunrxjavaexploring.R;

public class CombinOperaterActivtiy extends AppCompatActivity
{

    String TAG="CombinOperaterActivtiy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combin_operater_activtiy);
    }


    public void combineOperater(View view)
    {
        /*
         * There are 2 Observables each emitting values after an interval of 100 ms and 150 ms
         * respectively. The combineLatest() operator combines both the observables and
         * emits the result at each particular intervals.

         */
        Observable<Long> observable1 = Observable.interval(100, TimeUnit.MILLISECONDS);
        Observable<Long> observable2 = Observable.interval(50, TimeUnit.MILLISECONDS);

        Observable.combineLatest(observable1, observable2,
                (observable1Times, observable2Times) ->
                        "Refreshed Observable1: " + observable1Times + " refreshed Observable2: " + observable2Times)
                .subscribe(item -> Log.d(TAG, "combineOperater: "+item));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void joinOperater(View view)
    {
        /*
         * We create two Observables: left & right which emits a value every 100 milliseconds.
         * We join left Observable to the right Observable.
         * The two integers emitted from both the Observables are added
         * & the result is printed.
         *
         * */
        Observable<Long> left = Observable
                .interval(100, TimeUnit.MILLISECONDS);

        Observable<Long> right = Observable
                .interval(100, TimeUnit.MILLISECONDS);

        left.join(right,
                aLong -> Observable.timer(0, TimeUnit.SECONDS),
                aLong -> Observable.timer(0, TimeUnit.SECONDS),
                (l, r) -> {

                    Log.d(TAG, "Left result: " + l + " Right Result: " + r);
                    return l + r;
                })
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void mergeOperater(View view)
    {
        Observable<String> alphabets1 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "A" + id);

        Observable<String> alphabets2 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "B" + id);

        Observable.merge(alphabets1, alphabets2)
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


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     */
    public void concatOperater(View view)
    {
        Observable<String> alphabets1 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "A" + id);

        Observable<String> alphabets2 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "B" + id);

        Observable.concat(alphabets1, alphabets2)
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


    public void zipOperater(View view){
        /*
         * We create two Observables which emit an item evert second.
         * We combine the two Observables using zip() operator.
         * This combines the result of both operators together so we can
         * perform some modifications to the results and emit them.
         */
        Observable<String> alphabets1 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "A" + id);

        Observable<String> alphabets2 = Observable
                .interval(1, TimeUnit.SECONDS).map(id -> "B" + id);

        Observable.zip(alphabets1, alphabets2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2)  {
                return (s + " " + s2);
            }
        })
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


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("CheckResult")
    public void switchOnNext(View view)
    {
        Observable
                /* This is the outer observable. Items emitted here will be used to control the inner observable.
                 * Whenever it emits an item, the inner observable will stop its emission
                 * and a new one will be created.
                 */
                .switchOnNext(Observable.interval(600, TimeUnit.MILLISECONDS)

                        .map((aLong) -> {
                            /* This is the inner observable. It will emit items every 180ms.
                             * When the outer observable emits a new item (which is supposed to happen after 600ms)
                             * this one will be discarded and a new one will be taken in place.
                             * Since outer observable will emit items each 600ms, inner observable will have a chance to emit 3 items and
                             * then be discarded. */
                            return Observable.interval(180, TimeUnit.MILLISECONDS);
                        }))

                .subscribe(item -> Log.d(TAG, "switchOnNext: "+item));


    }


}
