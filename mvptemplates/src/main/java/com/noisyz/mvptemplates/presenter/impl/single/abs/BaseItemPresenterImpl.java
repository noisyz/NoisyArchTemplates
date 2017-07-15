package com.noisyz.mvptemplates.presenter.impl.single.abs;


import com.noisyz.mvptemplates.network.BaseRequestCallback;
import com.noisyz.mvptemplates.network.RequestInteractor;
import com.noisyz.mvptemplates.presenter.impl.BasePresenterImpl;
import com.noisyz.mvptemplates.view.ItemView;

import rx.Single;

/**
 * Created by nero232 on 28.04.17.
 */

public abstract class BaseItemPresenterImpl<T> extends BasePresenterImpl<ItemView<T>> implements BaseRequestCallback<T> {

    public BaseItemPresenterImpl(ItemView<T> tItemView) {
        super(tItemView);
    }

    public void requestData() {
        if (getView() != null)
            getView().showProgressView();
        Single<T> request = initRequest();
        if (request != null)
            new RequestInteractor<T>().makeRequest(request, this);
    }

    protected abstract Single<T> initRequest();

    @Override
    public void onRequestSuccess(T t) {
        if (getView() != null) {
            getView().onItemLoaded(t);
            getView().hideProgressView();
        }
    }
}
