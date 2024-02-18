package com.example.foodplanner.Network;

import android.content.Context;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.IngrdientResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection implements RemoteDataSource {
    public static final String  Base_url="https://www.themealdb.com/api/json/v1/1/";

    MealService mealService;

    private static Connection mealResponse=null;


    public Connection(Context context){
        Gson gson=new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        mealService = retrofit.create(MealService.class);
    }

    public static Connection getInstance(Context context){
        if(mealResponse==null){
            mealResponse=new Connection(context);
        }
        return mealResponse;
    }
    @Override
    public Observable<MealResponse> getRandomMeals() {
        Observable<MealResponse> observable=mealService.getRandomMeals().subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<CategoryResponse> getAllCategories() {
        Observable<CategoryResponse> observable=mealService.getAllCategories().subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getAllCountries() {
        Observable<MealResponse>observable=mealService.getAllCountries("list").subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getCategoryMeals(String category) {
        Observable<MealResponse>observable=mealService.getCategoryMeals(category).subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getCountryMeals(String country) {
        Observable<MealResponse>observable=mealService.getCountryMeals(country).subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getMealsByName(String name) {
        Observable<MealResponse>observable=mealService.getMealsByName(name).subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<IngrdientResponse> getAllIngrdient() {
        Observable<IngrdientResponse>observable=mealService.getAllIngrdient().subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getMealsByChar(String name) {
        Observable<MealResponse> observable=mealService.getMealsByChar(name).subscribeOn(Schedulers.io());
        return  observable;
    }

    @Override
    public Observable<MealResponse> getMealsOfCategory(String category) {
        Observable<MealResponse> observable=mealService.getMealsOfCategory(category).subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getMealsOfCountries(String country) {
        Observable<MealResponse>observable=mealService.getMealsOfCountries(country).subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<MealResponse> getMealsoFIngrdients(String ingredient) {
        Observable<MealResponse>observable=mealService.getMealsoFIngrdients(ingredient).subscribeOn(Schedulers.io());
        return observable;
    }

    @Override
    public Observable<CountryResponse> getfullofCountries() {
    Observable<CountryResponse>observable=mealService.getAllCountries().subscribeOn(Schedulers.io());
    return observable;
    }

}
