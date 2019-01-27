package me.arun.arunrxjavaexploring.RxOperaters.model;

import io.reactivex.Observable;

public class UserService {

    /**
     * Gets User from database, this should not be run in UI thread.
     */
    public User getUserFromDb() throws InterruptedException {
        Thread.sleep(10000);
        return new User(1L);
    }

    public Observable<User> getUserObservable() {
        try {
            return Observable.just(getUserFromDb());
        } catch (InterruptedException e) {
            return Observable.error(e);
        }
    }



}