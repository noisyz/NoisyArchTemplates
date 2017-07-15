package com.noisyz.mvptemplates.view;

import android.content.Context;

import com.noisyz.mvptemplates.presenter.BasePresenter;

public interface BasePresentableView {

    void showProgressView();

    void hideProgressView();

    BasePresenter initPresenter();

    void releasePresenter();

    void showError(String message);

    Context getContext();
}
