package com.noisyz.mvptemplates.presenter.cache.parser;

import java.util.List;

/**
 * Created by nero232 on 17.06.17.
 */

public interface ItemParser<T> {

    List<T> parseItems(String... data);

}
