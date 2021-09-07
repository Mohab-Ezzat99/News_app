package com.example.news.network;

import java.io.IOException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String API_KEY="b017dd7ae7574ca1915e391a6bc3ae19";
    private static final String BASE_URL="https://newsapi.org/v2/";
    private static Retrofit instance;

    public static Retrofit getInstance(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                            Request request = chain.request();
                            HttpUrl url = request.url().newBuilder().addQueryParameter("apiKey",API_KEY).build();
                            request = request.newBuilder().url(url).build();
                            return chain.proceed(request);
                        }).build();

        if(instance==null){
            instance=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }


}
