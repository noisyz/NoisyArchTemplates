package com.noisyz.mvptemplates.presenter.impl;


import com.noisyz.mvptemplates.network.BaseErrorCallback;
import com.noisyz.mvptemplates.presenter.BasePresenter;
import com.noisyz.mvptemplates.view.BasePresentableView;

/**
 * Created by nero232 on 13.02.17.
 */

public class BasePresenterImpl<T extends BasePresentableView> implements BasePresenter, BaseErrorCallback {

    private T t;

    public BasePresenterImpl(T t) {
        this.t = t;
    }

    protected T getView() {
        return t;
    }

    @Override
    public void release() {
        t = null;
    }

    @Override
    public void onRequestError(int errorCode, String errorMessage) {
        if (t != null) {
            t.showError(errorMessage);
            t.hideProgressView();
        }
    }
}
