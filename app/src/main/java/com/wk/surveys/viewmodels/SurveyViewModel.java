package com.wk.surveys.viewmodels;
import com.wk.data.entities.Survey;
import com.wk.domain.remote.bases.FindAllRemoteUseCase;
import com.wk.surveys.viewmodels.bases.BaseViewModel;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public class SurveyViewModel extends BaseViewModel<Survey> {
    public SurveyViewModel(FindAllRemoteUseCase<Survey> findAllRemoteUseCase) {
        super(findAllRemoteUseCase);
    }
}
