package com.wk.domain.remote.bases;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public interface FindAllRemoteUseCase<T> {
    Flowable<List<T>> findAll(Integer page, Integer perPage);
}
