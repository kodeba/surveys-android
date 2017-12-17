package com.wk.data.remote.repositories;

import com.wk.data.entities.Survey;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by watsaponk on 16/12/2017 AD.
 */

public interface SurveyRemoteRep {
    @GET("surveys.json")
    Flowable<List<Survey>> findAll(@Query("page") Integer page, @Query("per_page") Integer perPage);
}
