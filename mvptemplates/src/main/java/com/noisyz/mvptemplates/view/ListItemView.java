package com.noisyz.mvptemplates.view;

import com.noisyz.bindlibrary.callback.clickevent.OnItemClickListener;

import java.util.List;

/**
 * Created by nero232 on 19.04.17.
 */

public interface ListItemView<T> extends BasePresentableView, OnItemClickListener<T> {

    void onItemListLoaded(List<T> items);

    void requestData();

}
