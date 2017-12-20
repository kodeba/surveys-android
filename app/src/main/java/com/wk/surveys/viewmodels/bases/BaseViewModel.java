package com.wk.surveys.viewmodels.bases;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wk.domain.remote.bases.FindAllRemoteUseCase;
import com.wk.surveys.models.Response;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class BaseViewModel<T> extends ViewModel implements IBaseViewModel<T> {
    protected final FindAllRemoteUseCase<T> findAllRemoteUseCase;

    protected CompositeDisposable disposable = new CompositeDisposable();
    protected MutableLiveData<Response<List<T>>> response = new MutableLiveData<>();
    protected MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();
    protected MutableLiveData<Boolean> refreshNotify = new MutableLiveData<>();

    public BaseViewModel(FindAllRemoteUseCase<T> findAllRemoteUseCase) {
        this.findAllRemoteUseCase = findAllRemoteUseCase;
    }

    @Override
    public LiveData<Response<List<T>>> getResponse() {
        return response;
    }

    @Override
    public LiveData<Boolean> getLoadingStatus() {
        return loadingStatus;
    }

    @Override
    public LiveData<Boolean> getRefreshNotify() {
        return refreshNotify;
    }

    @Override
    public void loadRemoteData(Integer page, Integer perPage){
        refreshNotify.setValue(false);
        disposable.add(
                findAllRemoteUseCase.findAll(page, perPage).
                        subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(s-> loadingStatus.setValue(true))
                        .doAfterTerminate(()-> loadingStatus.setValue(false))
                        .subscribe(
                                result->response.setValue(Response.success(result)),
                                t->response.setValue(Response.error(t))
                        )
        );
    }

    @Override
    public void loadLocalData(){

    }

    @Override
    public void notifyRefresh() {
        refreshNotify.setValue(true);
    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }

}
