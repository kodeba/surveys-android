package com.wk.domain.remote.usecases.survey;

import com.wk.data.entities.Survey;
import com.wk.data.remote.datastores.SurveyRemoteDataStore;
import com.wk.domain.remote.bases.BaseRemoteUseCase;
import com.wk.domain.remote.bases.FindAllRemoteUseCase;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class FindAllSurveyUseCase extends BaseRemoteUseCase<SurveyRemoteDataStore> implements FindAllRemoteUseCase<Survey> {

    public FindAllSurveyUseCase(SurveyRemoteDataStore service) {
        super(service);
    }

    @Override
    public Flowable<List<Survey>> findAll(Integer page, Integer perPage) {
        return service.findAll(page, perPage);
    }
}
