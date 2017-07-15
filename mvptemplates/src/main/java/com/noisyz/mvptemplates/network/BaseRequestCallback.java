package com.noisyz.mvptemplates.network;

/**
 * Created by oleg on 21.04.16.
 */
public interface BaseRequestCallback<T> extends BaseErrorCallback{

    void onRequestSuccess(T t);

}
