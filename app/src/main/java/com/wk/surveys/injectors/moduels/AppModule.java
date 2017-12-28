package com.wk.surveys.injectors.moduels;

import android.content.Context;

import com.wk.data.remote.RemoteAPI;
import com.wk.data.remote.datastores.SurveyRemoteDataStore;
import com.wk.surveys.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */
@Module
public class AppModule {
    @Provides
    Context provideContext(App application){
        return application.getApplicationContext();
    }

    //Core
    @Singleton
    @Provides
    RemoteAPI provideRemoteAPI(){
        return new RemoteAPI();
    }

    //Survey
    @Singleton
    @Provides
    SurveyRemoteDataStore provideSurveyRemoteDataStore(RemoteAPI api){
        return new SurveyRemoteDataStore(api);
    }
}
