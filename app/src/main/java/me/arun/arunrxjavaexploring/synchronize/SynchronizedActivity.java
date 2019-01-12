package me.arun.arunrxjavaexploring.synchronize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.arun.arunrxjavaexploring.R;

public class SynchronizedActivity extends AppCompatActivity {

    public  String TAG ="SynchronizedActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronized);
        synchronisedCheck();
    }


    public void synchronisedCheck()
    {
        Sender snd = new Sender();
        ThreadedSend S1 =
                new ThreadedSend( " Hi " , snd );
        ThreadedSend S2 =
                new ThreadedSend( " Bye " , snd );

        // Start two threads of ThreadedSend type
        S1.start();
        S2.start();
        // wait for threads to end
        try
        {
            S1.join();
            S2.join();
        }
        catch(Exception e)
        {
            System.out.println("Interrupted");
            Log.d(TAG, "synchronisedCheck: ");
        }
    }
}
