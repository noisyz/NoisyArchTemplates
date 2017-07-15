package com.noisyz.mvptemplates.presenter.impl.list.abs;

import com.noisyz.mvptemplates.network.BaseRequestCallback;
import com.noisyz.mvptemplates.network.RequestInteractor;
import com.noisyz.mvptemplates.presenter.impl.BasePresenterImpl;
import com.noisyz.mvptemplates.view.ListItemView;

import java.util.List;

import rx.Single;
import rx.functions.Func1;

/**
 * Created by nero232 on 19.04.17.
 */

public abstract class BaseListPresenterImpl<T> extends BasePresenterImpl<ListItemView<T>>
        implements BaseRequestCallback<List<T>> {

    public BaseListPresenterImpl(ListItemView<T> tListItemView) {
        super(tListItemView);
    }

    public void requestItems() {
            makeRequest(initRequestObservable());
    }

    protected void makeRequest(Single<List<T>> single) {
        new RequestInteractor<List<T>>().makeRequest(single, this);
    }

    protected abstract Single<List<T>> initRequestObservable();

    @Override
    public void onRequestSuccess(List<T> ts) {
        if (getView() != null) {
            getView().onItemListLoaded(ts);
            getView().hideProgressView();
        }
    }


}
