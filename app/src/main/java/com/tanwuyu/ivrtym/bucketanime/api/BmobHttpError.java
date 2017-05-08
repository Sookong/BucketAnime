package com.tanwuyu.ivrtym.bucketanime.api;

import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by ivrty on 2017/4/25.
 */

public class BmobHttpError{
    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
