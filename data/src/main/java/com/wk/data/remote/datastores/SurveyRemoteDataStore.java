package com.wk.data.remote.datastores;

import com.wk.data.entities.Survey;
import com.wk.data.remote.bases.BaseRemoteDataStore;
import com.wk.data.remote.repositories.SurveyRemoteRep;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class SurveyRemoteDataStore extends BaseRemoteDataStore<Survey> {
    private final SurveyRemoteRep remoteRep;

    public SurveyRemoteDataStore() {
        super();
        this.remoteRep = retrofit.create(SurveyRemoteRep.class);
    }

    @Override
    public Flowable<List<Survey>> findAll(Integer page, Integer perPage) {
        return remoteRep.findAll(page, perPage);
    }
}
