package com.noisyz.mvptemplates.presenter.impl.list.abs;


import com.noisyz.mvptemplates.view.ListItemView;

/**
 * Created by nero232 on 22.05.17.
 */

public abstract class BaseListDateSelectablePresenter<T> extends BaseListPresenterImpl<T> {

    private long dateFrom, dateTill;

    public BaseListDateSelectablePresenter(ListItemView<T> tListItemView) {
        super(tListItemView);
    }

    public void setDates(long dateFrom, long dateTill) {
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public long getDateTill() {
        return dateTill;
    }
}
