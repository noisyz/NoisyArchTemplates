package com.noisyz.mvptemplates.ui.single.abs;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.mvptemplates.R;
import com.noisyz.mvptemplates.presenter.impl.single.abs.BaseItemPresenterImpl;
import com.noisyz.mvptemplates.view.ItemView;


/**
 * Created by nero232 on 28.04.17.
 */

public abstract class ItemFragment<T> extends Fragment implements ItemView<T> {

    private BaseItemPresenterImpl<T> baseItemPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseItemPresenter = initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ViewGroup contentContainer = (ViewGroup) view.findViewById(R.id.content_container);
        if (contentContainer != null) {
            View content = inflater.inflate(getContentLayoutId(), null);
            contentContainer.addView(content);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestItem();
        showProgressView();
    }


    protected int getLayoutId() {
        return R.layout.fragment_item;
    }

    protected abstract int getContentLayoutId();

    @Override
    public abstract BaseItemPresenterImpl<T> initPresenter();

    private void showEmptyView(View view) {
        if (view != null)
            view.findViewById(R.id.empty).setVisibility(View.VISIBLE);
    }

    private void hideEmptyView(View view) {
        if (view != null)
            view.findViewById(R.id.empty).setVisibility(View.GONE);
    }

    @Override
    public void showProgressView() {
        changeProgressVisibility(View.VISIBLE);
        changeContentVisibility(View.GONE);
    }

    @Override
    public void hideProgressView() {
        changeProgressVisibility(View.GONE);
        changeContentVisibility(View.VISIBLE);
    }

    private void changeProgressVisibility(int visibility) {
        if (getView() != null) {
            View progressView = getView().findViewById(R.id.progress);
            if (progressView != null)
                progressView.setVisibility(visibility);
        }
    }

    private void changeContentVisibility(int visibility) {
        if (getView() != null) {
            View progressView = getView().findViewById(R.id.content_container);
            if (progressView != null)
                progressView.setVisibility(visibility);
        }
    }

    @Override
    public void onItemLoaded(T t) {
        if (t != null) {
            hideEmptyView(getView());
            showItem(t);
        } else {
            showEmptyView(getView());
        }
    }

    protected abstract void showItem(T t);

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePresenter();
    }

    @Override
    public void releasePresenter() {
        baseItemPresenter.release();
        baseItemPresenter = null;
    }

    @Override
    public void showError(String message) {
        if (getView() != null)
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void requestItem() {
        baseItemPresenter.requestData();
    }

    public BaseItemPresenterImpl<T> getPresenter() {
        return baseItemPresenter;
    }
}
