package com.wk.surveys.models;

import javax.annotation.Nullable;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class Response<T> {
    public static final Boolean ERROR = false;
    public static final Boolean SUCCESS = true;

    public Boolean status;
    @Nullable
    public T data;
    @Nullable
    public Throwable error;

    public Response(Boolean status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS, data, null);
    }

    public static <T> Response<T> error(Throwable error) {
        return new Response<>(ERROR, null, error);
    }
}
