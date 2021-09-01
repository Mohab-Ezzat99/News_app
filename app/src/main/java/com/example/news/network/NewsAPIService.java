package com.example.news.network;

import com.example.news.model.MainNewsModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIService {

    @GET("top-headlines")
    Single<MainNewsModel> getNews(@Query("country") String country,
                                  @Query("pageSize") int pageSize);

    @GET("top-headlines")
    Single<MainNewsModel> getCategoryNews(@Query("country") String country,
                                @Query("category") String category,
                                @Query("pageSize") int pageSize);
}
