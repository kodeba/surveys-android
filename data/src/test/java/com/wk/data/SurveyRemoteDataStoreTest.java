package com.wk.data;

import com.wk.data.entities.Survey;
import com.wk.data.remote.RemoteAPI;
import com.wk.data.remote.datastores.SurveyRemoteDataStore;
import org.junit.Test;
import java.util.List;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by watsaponk on 28/12/2017 AD.
 */

public class SurveyRemoteDataStoreTest {
    private static final Integer PAGE = 1;
    private static final Integer PER_PAGE = 5;

    @Test
    public void FindAll_CorrectParameter_Success(){
        RemoteAPI api = new RemoteAPI();
        SurveyRemoteDataStore surveyRemoteDataStore = new SurveyRemoteDataStore(api);
        TestSubscriber<List<Survey>> testSubscriber = new TestSubscriber<>();

        surveyRemoteDataStore
                .findAll(PAGE,PER_PAGE)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(Schedulers.trampoline())
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertComplete();
        testSubscriber.assertValue(value-> value.size() == PER_PAGE);
    }

    @Test
    public void FindAll_NoAccessToken_ReturnHttpStatus401(){
        RemoteAPI api = new RemoteAPI();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteAPI.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(RemoteAPI.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api.setRetrofit(retrofit);
        SurveyRemoteDataStore surveyRemoteDataStore = new SurveyRemoteDataStore(api);
        TestSubscriber<List<Survey>> testSubscriber = new TestSubscriber<>();

        surveyRemoteDataStore
                .findAll(PAGE,PER_PAGE)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(Schedulers.trampoline())
                .subscribe(testSubscriber);

        testSubscriber.assertError(throwable -> {
            HttpException exception = (HttpException) throwable;
            return exception.code() == 401;
        });
    }
}
