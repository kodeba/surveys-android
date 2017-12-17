package com.wk.data.remote.bases;


import com.wk.data.remote.RemoteAPI;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Retrofit;

/**
 * Created by watsaponk on 1/12/2017 AD.
 */

public abstract class BaseRemoteDataStore<T> {
    protected final Retrofit retrofit;

    public BaseRemoteDataStore() {
        RemoteAPI api = new RemoteAPI();
        retrofit = api.getRetrofit();
    }

    public abstract Flowable<List<T>> findAll(Integer page, Integer perPage);

}
