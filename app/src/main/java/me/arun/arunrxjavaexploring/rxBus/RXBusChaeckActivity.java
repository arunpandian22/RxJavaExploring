package me.arun.arunrxjavaexploring.rxBus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.arun.arunrxjavaexploring.R;
import me.arun.arunrxjavaexploring.User;

public class RXBusChaeckActivity extends AppCompatActivity
{
    CompositeDisposable disposable=new CompositeDisposable();
    CompositeDisposable disposable1=new CompositeDisposable();
    public String TAG="RXBusChaeckActivity";
    Button btUser,btNonUser;
    TextView tvEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus_chaeck);
        btNonUser=(Button)findViewById(R.id.btNonUser);
        btUser=(Button)findViewById(R.id.btUser);
        tvEvent=(TextView)findViewById(R.id.tvEvent);
        final RxBusUtil rxBusUtil=new RxBusUtil();
        disposable.add(rxBusUtil.toObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
           @Override
           public void accept(Object o)
           {
               if (o instanceof User)
               {
                   tvEvent.setText("it is a user event");
                   Log.d(TAG, "accept: ");
               }
               else{
                   tvEvent.setText("it is a non user event");
                   Log.d(TAG, "accept: No");
               }
           }
       }));

        disposable1.add(rxBusUtil.toObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception
            {
                if (o instanceof User)
                {
                    //tvEvent.setText("it is a user event");
                    Log.d(TAG, "accept1: ");
                }
                else{
                   // tvEvent.setText("it is a non user event");
                    Log.d(TAG, "accept1: No");
                }
            }
        }));
        btUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                User user=new User();
                user.firstname="ARUN";
                user.lastname="PANDIAN";
                rxBusUtil.send(user);
            }
        });
        btNonUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                rxBusUtil.send("String");
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        disposable.dispose();
    }

}
