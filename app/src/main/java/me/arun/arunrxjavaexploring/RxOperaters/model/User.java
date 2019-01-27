package me.arun.arunrxjavaexploring.RxOperaters.model;

import io.reactivex.Observable;

public class User {

    public final long id;

    public User(long id) {
        this.id = id;
    }

    public Observable<Long> valueObservable() {
        return Observable.just(id);
    }
}