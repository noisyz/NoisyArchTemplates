package com.noisyz.mvptemplates.presenter.cache.collector;

import com.noisyz.mvptemplates.presenter.cache.parser.ItemParser;

import java.util.List;

/**
 * Created by nero232 on 17.06.17.
 */

public abstract class Collector<T> implements ItemParser<T> {

    private List<T> collectedData;

    public List<T> collect(String trackersXml, String markersXml) {
        collectedData = parseItems(trackersXml, markersXml);
        return collectedData;
    }

    public void release() {
        if (collectedData != null) {
            collectedData.clear();
            collectedData = null;
        }
    }

    public List<T> getCollectedData() {
        return collectedData;
    }
}
