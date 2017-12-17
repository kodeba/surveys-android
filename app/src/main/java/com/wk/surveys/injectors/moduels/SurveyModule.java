package com.wk.surveys.injectors.moduels;

import com.wk.data.entities.Survey;
import com.wk.data.remote.datastores.SurveyRemoteDataStore;
import com.wk.domain.remote.bases.FindAllRemoteUseCase;
import com.wk.domain.remote.usecases.survey.FindAllSurveyUseCase;
import com.wk.surveys.viewmodels.factories.SurveyViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */
@Module
public class SurveyModule {
    @Provides
    SurveyViewModelFactory provdeSurveyViewModelFactory(FindAllRemoteUseCase<Survey> findAllRemoteUseCase){
        return new SurveyViewModelFactory(findAllRemoteUseCase);
    }

    @Provides
    FindAllRemoteUseCase<Survey> provideSurveyFindAllRemoteUseCase(SurveyRemoteDataStore surveyRemoteDataStore){
        return new FindAllSurveyUseCase(surveyRemoteDataStore);
    }
}
