package com.wk.surveys.viewmodels.bases;

import android.arch.lifecycle.LiveData;

import com.wk.surveys.models.Response;

import java.util.List;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public interface IBaseViewModel<T> {
    LiveData<Response<List<T>>> getResponse();
    LiveData<Boolean> getLoadingStatus();
    LiveData<Boolean> getRefreshNotify();
    void loadRemoteData(Integer page, Integer perPage);
    void loadLocalData();
    void notifyRefresh();
}
