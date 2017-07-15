package com.noisyz.mvptemplates.network;


import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Oleg on 02.03.2016.
 */
public class RequestInteractor<T> {

    public void makeRequest(Single<T> observable, BaseRequestCallback<T> callback) {
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new DefaultHandler<>(callback));
    }
}
