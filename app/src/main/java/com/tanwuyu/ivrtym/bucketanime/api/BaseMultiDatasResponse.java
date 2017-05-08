package com.tanwuyu.ivrtym.bucketanime.api;

import java.util.List;

/**
 * Created by ivrtym on 2017/2/23.
 */

public class BaseMultiDatasResponse<T> {
    private List<T> results;
    private int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
