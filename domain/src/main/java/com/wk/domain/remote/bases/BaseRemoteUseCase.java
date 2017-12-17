package com.wk.domain.remote.bases;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class BaseRemoteUseCase<T> {
    protected final T service;

    public BaseRemoteUseCase(T service) {
        this.service = service;
    }
}
