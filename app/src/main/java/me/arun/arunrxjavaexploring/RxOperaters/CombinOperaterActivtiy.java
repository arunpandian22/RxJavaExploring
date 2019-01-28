package me.arun.arunrxjavaexploring.RxOperaters;
import android.annotation.SuppressLint;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

        Observable<Integer> observable1 = Observable.just(1, 2, 3,7);
        Observable<Integer> observable2 = Observable.just(2,4,5);

        List<Observable<Integer>> observableList = Arrays.asList(observable1, observable2);



        Observable observable = Observable.combineLatest(observableList, new Function<Object[], Integer>() {

            @Override
            public Integer apply(Object... objects) throws Exception {
                int concat = 0;
                for (Object value : objects) {
                    if (value instanceof Integer) {
                        Log.d(TAG, "apply: "+value);
                        concat += (Integer) value;
                    }
                }
                return concat;
            }
        });

        observable.subscribe(value -> Log.d(TAG, "combineOperater: "+value));
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
        Observable
                .merge(getMaleObservable(), getFemaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, user.getName() + ", " + user.getGender());
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
    public void concatOperater(View view) {
        Observable
                .concat(getMaleObservable(), getFemaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, user.getName() + ", " + user.getGender());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void zipOperater(View view)
    {
        /*
         * We create two Observables which emit an item evert second.
         * We combine the two Observables using zip() operator.
         * This combines the result of both operators together so we can
         * perform some modifications to the results and emit them.
         */



//        Observable.zip(getMaleObservable(), getFemaleObservable(),
//                new BiFunction<List<User>, List<User>, List<User>>() {
//                    @Override
//                    public List<User> apply(List<User> maleUser, List<User> femaleUser) {
//List<User> zippedUser=new ArrayList<>();
//                        for (User male : maleUser) {
//                            for (User female : femaleUser) {
//                                if (male.id == female.id) {
//                                    zippedUser.add(male);
//                                }
//                            }
//                        }
//                        return zippedUser;
//                    }
//                })
//                // Run on a background thread
//                .subscribeOn(Schedulers.io())
//                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Ob);

//        Observable.zip(alphabets1, alphabets2, new BiFunction<String, String, String>() {
//            @Override
//            public String apply(String s, String s2)  {
//                return (s + " " + s2);
//            }
//        })
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                        Log.d(TAG, "onNext: "+s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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




    private Observable<User> getFemaleObservable() {
        String[] names = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("female");

            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(1000);
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    private Observable<User> getMaleObservable() {
        String[] names = new String[]{"Mark", "John", "Trump", "Obama"};

        final List<User> users = new ArrayList<>();

        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(500);
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
}
