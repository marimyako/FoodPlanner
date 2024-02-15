package com.example.foodplanner.Model;

import android.content.Context;

import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.Network.RemoteDataSource;

public class Repository implements  RepositoryInterface{

    private Context context;

    RemoteDataSource remoteDataSource;
    private static  Repository repository=null;

    public Repository(RemoteDataSource remoteDataSource,Context context) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(RemoteDataSource remoteDataSource,Context context){
        if (repository==null){
            repository=new Repository(remoteDataSource,context);
        }
        return repository;
    }
    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        remoteDataSource.getRandomMeals(networkCallBack);
    }

    @Override
    public void getAllCategories(NetworkCallBack networkCallBack) {
        remoteDataSource.getAllCategories(networkCallBack);
    }

    @Override
    public void getAllCountries(NetworkCallBack networkCallBack) {
        remoteDataSource.getAllCountries(networkCallBack);
    }

    @Override
    public void getCategoryMeals(NetworkCallBack networkCallBack, String category) {
        remoteDataSource.getCategoryMeals(networkCallBack,category);
    }

    @Override
    public void getCountryMeals(NetworkCallBack networkCallBack, String country) {
        remoteDataSource.getCountryMeals(networkCallBack,country);
    }

    @Override
    public void getMealsByName(NetworkCallBack networkCallBack, String name) {
          remoteDataSource.getMealsByName(networkCallBack,name);
    }
}
