package com.example.news.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.news.model.MainNewsModel;
import com.example.news.repository.Repository;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

public class NewsViewModel extends AndroidViewModel {

    private final Repository repository;
    private final MutableLiveData<Boolean> mlHomeNews = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mlSportsNews = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mlHealthNews = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mlScienceNews = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mlEntertainmentNews = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mlTechnologyNews = new MutableLiveData<>();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository();
    }

    public Single<MainNewsModel> getNews(String country, int pageSize){
        return repository.getNews(country,pageSize);
    }

    public Single<MainNewsModel> getCategoryNews(String country,String category,int pageSize){
        return repository.getCategoryNews(country,category,pageSize);
    }

    public MutableLiveData<Boolean> refreshHomeNews(){
        mlHomeNews.setValue(true);
        return mlHomeNews;
    }

    public MutableLiveData<Boolean> refreshSportsNews(){
        mlSportsNews.setValue(true);
        return mlSportsNews;
    }

    public MutableLiveData<Boolean> refreshHealthNews(){
        mlHealthNews.setValue(true);
        return mlHealthNews;
    }

    public MutableLiveData<Boolean> refreshScienceNews(){
        mlScienceNews.setValue(true);
        return mlScienceNews;
    }

    public MutableLiveData<Boolean> refreshEntertainmentNews(){
        mlEntertainmentNews.setValue(true);
        return mlEntertainmentNews;
    }

    public MutableLiveData<Boolean> refreshTechnologyNews(){
        mlTechnologyNews.setValue(true);
        return mlTechnologyNews;
    }
}
