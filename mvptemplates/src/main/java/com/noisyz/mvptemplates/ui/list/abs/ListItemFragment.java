package com.noisyz.mvptemplates.ui.list.abs;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noisyz.bindlibrary.base.impl.adapter.RecyclerBindAdapter;
import com.noisyz.mvptemplates.R;
import com.noisyz.mvptemplates.presenter.impl.list.abs.BaseListPresenterImpl;
import com.noisyz.mvptemplates.view.ListItemView;

import java.util.List;


/**
 * Created by nero232 on 19.04.17.
 */

public abstract class ListItemFragment<T> extends Fragment implements ListItemView<T>,
        SwipeRefreshLayout.OnRefreshListener {

    private BaseListPresenterImpl<T> baseListPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerBindAdapter<T> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseListPresenter = initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(getListLayoutId(), null);
        initRecycler(view);
        initSwipeRefreshLayout(view);
        return view;
    }

    protected int getListLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestData();
        showProgressView();
    }

    @Override
    public void requestData() {
        baseListPresenter.requestItems();
    }


    private void initSwipeRefreshLayout(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
            swipeRefreshLayout.setOnRefreshListener(this);
        }
    }

    private void initRecycler(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initAdapter(List<T> items, View view) {
        adapter = initItemsAdapter(items);
        adapter.setOnItemClickListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemListLoaded(List<T> items) {
        if (getView() != null) {
            if (adapter != null) {
                if (items != null) {
                    adapter.setItems(items);
                }
            } else {
                initAdapter(items, getView());
            }

            if (adapter.getItemCount() == 0 || items == null) {
                showEmptyView(getView());
            } else {
                hideEmptyView(getView());
            }
        }
    }

    private void showEmptyView(View view) {
        view.findViewById(R.id.empty).setVisibility(View.VISIBLE);
    }

    private void hideEmptyView(View view) {
        view.findViewById(R.id.empty).setVisibility(View.GONE);
    }

    @Override
    public void showProgressView() {
        if (adapter != null && adapter.getItems() != null && adapter.getItems().isEmpty()) {
            changeProgressVisibility(View.VISIBLE);
            changeContentVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgressView() {
        changeProgressVisibility(View.GONE);
        changeContentVisibility(View.VISIBLE);
    }

    protected void changeProgressVisibility(int visibility) {
        if (getView() != null) {
            View progressView = getView().findViewById(R.id.progress);
            if (progressView != null)
                progressView.setVisibility(visibility);
            if (swipeRefreshLayout != null)
                swipeRefreshLayout.setRefreshing(false);
        }
    }

    protected void changeContentVisibility(int visibility) {
        if (getView() != null) {
            View progressView = getView().findViewById(R.id.list);
            if (progressView != null)
                progressView.setVisibility(visibility);
        }
    }

    @Override
    public abstract BaseListPresenterImpl<T> initPresenter();

    @Override
    public void releasePresenter() {
        baseListPresenter.release();
        baseListPresenter = null;
    }

    @Override
    public void showError(String message) {
        if (getView() != null)
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    protected abstract RecyclerBindAdapter<T> initItemsAdapter(List<T> items);

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        if (baseListPresenter != null)
            baseListPresenter.requestItems();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePresenter();
    }

    public BaseListPresenterImpl<T> getPresenter() {
        return baseListPresenter;
    }

    public RecyclerBindAdapter<T> getAdapter() {
        return adapter;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
