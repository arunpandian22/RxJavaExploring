package me.arun.arunrxjavaexploring.synchronize;

import android.util.Log;

class Sender
{
    String TAG="Sender";
    public void send(String msg)
    {
        System.out.println("Sending\t"  + msg );
        try
        {
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
        System.out.println("\n" + msg + "Sent");
        Log.d(TAG, "send: "+msg+" Sent");
    }
}
