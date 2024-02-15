package com.example.foodplanner.Network;

import android.content.Context;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
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
        File cacheDirectory = new File(context.getCacheDir(), "offline_cache_directory");
        Cache cache = new Cache(cacheDirectory,100 *1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder().cache(cache).build();

        Retrofit.Builder retrofitB = new Retrofit.Builder()
                .baseUrl(Base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create());

        Retrofit retrofit = retrofitB.build();

        mealService = retrofit.create(MealService.class);
    }

    public static Connection getInstance(Context context){
        if(mealResponse==null){
            mealResponse=new Connection(context);
        }
        return mealResponse;
    }
    @Override
    public void getRandomMeals(NetworkCallBack networkCallBack) {

        List<Meal> list=new ArrayList<>();
        Flowable<MealResponse> mealModelResponseSingle= mealService.getRandomMeals();
        mealModelResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .repeat(10)
                .doOnNext(e-> list.addAll(e.getMealsModel()))
                .doOnComplete(()->networkCallBack.onSuccessResultRandom(list))
                .subscribe();

    }

    @Override
    public void getAllCategories(NetworkCallBack networkCallBack) {
        Single<CategoryResponse> categoryResponseSingle= mealService.getAllCategories();
        categoryResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .subscribe(item -> networkCallBack.onSuccessResultCategories(item.getCategories()));
    }

    @Override
    public void getAllCountries(NetworkCallBack networkCallBack) {
        Single<MealResponse> countryResponseSingle= mealService.getAllCountries("list");
        countryResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .subscribe(item -> networkCallBack.onSuccessResultCountries(item.getMealsModel()));
    }

    @Override
    public void getCategoryMeals(NetworkCallBack networkCallBack, String category) {
        Single<MealResponse> mealModelResponseSingle= mealService.getCategoryMeals(category);
        mealModelResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .subscribe(item -> networkCallBack.onSuccessResultRandom(item.getMealsModel()));
    }

    @Override
    public void getCountryMeals(NetworkCallBack networkCallBack, String country) {
        Single<MealResponse> mealModelResponseSingle= mealService.getCountryMeals(country);
        mealModelResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .onErrorComplete()
                .subscribe(item -> networkCallBack.onSuccessResultRandom(item.getMealsModel()));
    }

    @Override
    public void getMealsByName(NetworkCallBack networkCallBack, String name) {
        Single<MealResponse> mealModelResponseSingle= mealService.getMealsByName(name);
        mealModelResponseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> networkCallBack.onSuccessResultRandom(item.getMealsModel()));
    }


}
