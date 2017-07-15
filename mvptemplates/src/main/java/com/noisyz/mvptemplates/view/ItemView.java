package com.noisyz.mvptemplates.view;

/**
 * Created by nero232 on 28.04.17.
 */

public interface ItemView<T> extends BasePresentableView{

    void onItemLoaded(T t);

    void requestItem();

}
