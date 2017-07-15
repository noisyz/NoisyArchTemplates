package com.noisyz.mvptemplates.network;

import rx.Subscriber;

/**
 * Created by Oleg on 25.02.2016.
 */
public class DefaultHandler<T> extends Subscriber<T>{

    private static final int UNKNOWN_ERROR = -1;

    private BaseRequestCallback<T> callback;

    public DefaultHandler(BaseRequestCallback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        callback.onRequestError(UNKNOWN_ERROR, e.getLocalizedMessage());
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        callback.onRequestSuccess(t);
    }
}
