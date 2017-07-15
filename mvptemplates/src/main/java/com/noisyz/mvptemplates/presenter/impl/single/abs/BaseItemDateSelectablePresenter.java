package com.noisyz.mvptemplates.presenter.impl.single.abs;


import com.noisyz.mvptemplates.view.ItemView;

/**
 * Created by nero232 on 22.05.17.
 */

public abstract class BaseItemDateSelectablePresenter<T> extends BaseItemPresenterImpl<T> {

    private long dateFrom, dateTill;

    public BaseItemDateSelectablePresenter(ItemView<T> tItemView) {
        super(tItemView);
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
