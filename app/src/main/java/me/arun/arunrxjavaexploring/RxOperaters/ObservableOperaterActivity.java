package me.arun.arunrxjavaexploring.RxOperaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.arun.arunrxjavaexploring.R;

public class ObservableOperaterActivity extends AppCompatActivity
{
   List<String> alphabets=new ArrayList<>();
   Observer createObserver;
   Observable createObservable;
   String TAG="ObservableOperaterActivity";
   Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_operater);
//        Observable observable = Observable.just("A", "B", "C", "D", "E", "F");
        alphabets = getAlphabets();
        createObserver=createObserver();
        createObservable=getCreateObservable();


    }

    // in single emission
    public void justOperater(View view)
    {
        Observable.just(new String[]{"A", "B", "C", "D", "E", "F","A", "B", "C", "D", "E", "F"})
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        Log.d(TAG, "onNext: "+strings.toString());
                        Log.d(TAG, "onNext: "+Arrays.toString(strings));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void rangeOperater(View view)
    {

        Observable.range(2, 5) // emits 2 to 6
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void repeatOperater(View view)
    {
        Observable.range(2, 5)
                .repeat(2) // 2 times repeats the same values
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void timerInterval(View view) {
        Observable.timer(1, TimeUnit.SECONDS)
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
    }

    public void subscribeCreateObserver(View view){

        /*
         * We can call this method to subscribe
         * the observer to the Observable.
         * */
        createObservable.subscribe(createObserver);
    }


    /**
     * A method to make observable from Array using FromArray operator
     */
    public void fromCallable(View view)
    {

        Observable.fromArray(new String[]{"A", "B", "C", "D", "E", "F"})
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {

                        Log.d(TAG, "onNext: "+string);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void intervalOperater(View view)
    {

        /*
         * This will print values from 0 after every second.
         */
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        disposable=d;
                    }

                    @Override
                    public void onNext(Long value)
                    {
                        Log.d(TAG, "onNext: "+value);
                        if(value>10 &&disposable!=null)
                            disposable.dispose();
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









    Observable getCreateObservable(){
        /*
         * Observable.create() -> We will need to call the
         * respective methods of the emitter such as onNext()
         * & onComplete() or onError()
         *
         * */
       return  Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) {

                try {

                    /*
                     * The emitter can be used to emit each list item
                     * to the subscriber.
                     *
                     * */
                    for (String alphabet : alphabets) {
                        emitter.onNext(alphabet);
                    }

                    /*
                     * Once all the items in the list are emitted,
                     * we can call complete stating that no more items
                     * are to be emitted.
                     *
                     * */
                    emitter.onComplete();

                } catch (Exception e) {

                    /*
                     * If an error occurs in the process,
                     * we can call error.
                     *
                     * */
                    emitter.onError(e);
                }
            }
        });
    }


    /**
     *
     * @return
     */
    public  Observer createObserver()
    {
        /*
         * We create an Observer that is subscribed to Observer.
         * The only function of the Observer in this scenario is
         * to print the valeus emitted by the Observer.
         *
         * */
        return  new Observer()
        {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: "+o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

    }






    public List<String> getAlphabets() {
        alphabets.add("a");
        alphabets.add("b");
        alphabets.add("c");
        alphabets.add("d");
        alphabets.add("e");
        return alphabets;
    }
}
