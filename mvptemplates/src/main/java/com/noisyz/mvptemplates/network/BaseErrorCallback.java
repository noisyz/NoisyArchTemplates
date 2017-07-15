package com.noisyz.mvptemplates.network;

/**
 * Created by nero232 on 13.02.17.
 */

public interface BaseErrorCallback {
    void onRequestError(int errorCode, String errorMessage);
}
