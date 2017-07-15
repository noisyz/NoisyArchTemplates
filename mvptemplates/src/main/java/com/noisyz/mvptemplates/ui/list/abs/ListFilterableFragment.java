package com.noisyz.mvptemplates.ui.list.abs;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.noisyz.bindlibrary.base.impl.adapter.RecyclerFilterableBindAdapter;
import com.noisyz.bindlibrary.callback.filter.QueryItemCallback;
import com.noisyz.mvptemplates.R;


import java.util.List;

public abstract class ListFilterableFragment<T> extends ListItemFragment<T> implements QueryItemCallback<T>, SearchView.OnQueryTextListener {
    private String query;
    private RecyclerFilterableBindAdapter<T, String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    protected RecyclerFilterableBindAdapter<T, String> initItemsAdapter(List<T> items) {
        adapter = initFilterableItemsAdapter(items);
        return adapter;
    }

    @Override
    public void onItemListLoaded(List<T> items) {
        super.onItemListLoaded(items);
        if (!TextUtils.isEmpty(query) && adapter != null)
            adapter.filter(query);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        query(newText);
        return true;
    }

    protected void query(String query) {
        this.query = query;
        if (query.isEmpty()) {
            adapter.restore();
        } else {
            adapter.filter(query);
        }
    }

    protected abstract RecyclerFilterableBindAdapter<T, String> initFilterableItemsAdapter(List<T> items);

}
