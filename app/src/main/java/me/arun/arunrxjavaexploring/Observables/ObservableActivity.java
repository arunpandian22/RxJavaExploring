package me.arun.arunrxjavaexploring.Observables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.User;

public class ObservableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
    }

//    public void single(){
//        Single.create(new SingleOnSubscribe<User>() {
//            @Override
//            public void subscribe(SingleEmitter<User> emitter) throws Exception {
//                User user = new User("Anitaa");
//                emitter.onSuccess(user);
//            }
//        })
//                .observeOn(Schedulers.io())
//                .subscribe(new SingleObserver<User>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(User user) {
//                        System.out.println(String.format("User with name %s successfully created: ", user.getName()));
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//    }
}
