package com.example.news.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.news.model.MainNewsModel;
import com.example.news.repository.Repository;

import io.reactivex.rxjava3.core.Single;

public class NewsViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

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

    public MutableLiveData<Boolean> refreshNews(){
        mutableLiveData.setValue(true);
        return mutableLiveData;
    }
}
