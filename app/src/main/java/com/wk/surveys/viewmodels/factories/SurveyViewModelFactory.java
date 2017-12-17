package com.wk.surveys.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.wk.data.entities.Survey;
import com.wk.domain.remote.bases.FindAllRemoteUseCase;
import com.wk.surveys.viewmodels.SurveyViewModel;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class SurveyViewModelFactory implements ViewModelProvider.Factory {
    private final FindAllRemoteUseCase<Survey> findAllRemoteUseCase;

    public SurveyViewModelFactory(FindAllRemoteUseCase<Survey> findAllRemoteUseCase) {
        this.findAllRemoteUseCase = findAllRemoteUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(SurveyViewModel.class)){
            return (T) new SurveyViewModel(findAllRemoteUseCase);
        }
        throw  new IllegalArgumentException("Unknown ViewModel class");
    }
}
