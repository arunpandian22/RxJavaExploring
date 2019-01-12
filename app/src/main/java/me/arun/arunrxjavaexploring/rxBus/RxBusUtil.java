package me.arun.arunrxjavaexploring.rxBus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBusUtil
{
    PublishSubject<Object> bus;
    public RxBusUtil()
    {
        bus = PublishSubject.create();
    }

     /**
     * Subscribe to this Observable. On event, do something
     * e.g. replace a fragment
     */

    public Observable<Object> toObservable()
    {
        return bus;
    }

    public void send(Object o)
    {
        bus.onNext(o);
    }

}
