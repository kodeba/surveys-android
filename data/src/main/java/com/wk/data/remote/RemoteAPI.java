package com.wk.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wk.data.remote.deserializers.BooleanTypeAdapter;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by watsaponk on 1/12/2017 AD.
 */

public class RemoteAPI {
    public static final String END_POINT = "https://nimbl3-survey-api.herokuapp.com/";
    private Retrofit retrofit = null;

    public RemoteAPI() {

    }

    public Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create(RemoteAPI.getGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getSafeOkHttpClient())
                    .build();
        }

        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public static Gson getGson(){
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapter(Boolean.class, new BooleanTypeAdapter())
                .create();
    }

    private OkHttpClient getSafeOkHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("access_token", "d9584af77d8c0d6622e2b3c554ed520b2ae64ba0721e52daa12d6eaa5e5cdd93")
                                .build();

                        Request request = original.newBuilder()
                                .url(url)
                                .header("Content-Type", "application/json")
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                }).build();
    }

}
