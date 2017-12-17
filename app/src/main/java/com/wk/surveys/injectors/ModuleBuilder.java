package com.wk.surveys.injectors;

import com.wk.surveys.injectors.moduels.SurveyModule;
import com.wk.surveys.views.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */
@Module
public abstract class ModuleBuilder {
    @ContributesAndroidInjector(modules = {SurveyModule.class})
    abstract MainActivity bindMainActivity();
}
