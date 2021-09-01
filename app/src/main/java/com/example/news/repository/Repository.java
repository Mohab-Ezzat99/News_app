package com.example.news.repository;

import com.example.news.model.MainNewsModel;
import com.example.news.network.NewsAPIService;
import com.example.news.network.RetrofitBuilder;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Query;

public class Repository {

    private NewsAPIService apiService= RetrofitBuilder.getInstance().create(NewsAPIService.class);

    public Single<MainNewsModel> getNews(String country,int pageSize){
        return apiService.getNews(country,pageSize);
    }

    public Single<MainNewsModel> getCategoryNews(String country,String category,int pageSize){
        return apiService.getCategoryNews(country,category,pageSize);
    }
}
