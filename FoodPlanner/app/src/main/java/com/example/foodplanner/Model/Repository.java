package com.example.foodplanner.Model;

import android.content.Context;

import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Network.RemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class Repository implements  RepositoryInterface{

    private Context context;

    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;

    public Repository(Context context, RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    private static  Repository repository=null;



    public static Repository getInstance(RemoteDataSource remoteDataSource,Context context, LocalDataSource localDataSource){
        if (repository==null){
            repository=new Repository(context,remoteDataSource,localDataSource);
        }
        return repository;
    }
    @Override
    public Observable<MealResponse> getRandomMeal() {
     return  remoteDataSource.getRandomMeals();
    }

    @Override
    public Observable<CategoryResponse> getAllCategories() {
      return   remoteDataSource.getAllCategories();
    }

    @Override
    public Observable<MealResponse> getAllCountries() {
       return remoteDataSource.getAllCountries();
    }

    @Override
    public Observable<MealResponse> getCategoryMeals(String category) {
       return remoteDataSource.getCategoryMeals(category);
    }

    @Override
    public Observable<MealResponse> getCountryMeals(String country) {
       return remoteDataSource.getCountryMeals(country);
    }

    @Override
    public Observable<MealResponse> getMealsByName(String name) {
       return remoteDataSource.getMealsByName(name);
    }

    @Override
    public Observable<MealPlanResponse> getMealsPlansByName(String name) {
        return remoteDataSource.getMealsPlansByName(name);
    }

    @Override
    public Observable<IngrdientResponse> getAllIngrdient() {
        return remoteDataSource.getAllIngrdient();
    }

    @Override
    public Observable<MealResponse> getMealsByChar(String name) {
        return remoteDataSource.getMealsByChar(name);
    }

    @Override
    public Observable<MealResponse> getMealsOfCategory(String category) {
        return remoteDataSource.getMealsOfCategory(category);
    }

    @Override
    public Observable<MealResponse> getMealsOfCountries(String country) {
        return remoteDataSource.getMealsOfCountries(country);
    }

    @Override
    public Observable<MealResponse> getMealsoFIngrdients(String ingredient) {
        return remoteDataSource.getMealsoFIngrdients(ingredient);
    }

    @Override
    public Observable<CountryResponse> getFullofCountries() {
        return remoteDataSource.getfullofCountries();
    }


    @Override
    public Completable insertmeal(Meal meal) {
        return localDataSource.insertmeal(meal);
    }

    @Override
    public Completable deletemeal(Meal meal) {
        return localDataSource.deletemeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeals() {
        return localDataSource.getAllStoredMeal();
    }

    @Override
    public Completable insertmealplan(MealPlan mealPlan) {
        return localDataSource.insertmealplan(mealPlan);
    }

    @Override
    public Completable deletemealplan(MealPlan mealPlan) {
        return localDataSource.deletemealplan(mealPlan);
    }

    @Override
    public Flowable<List<MealPlan>> getStoredMealsplan() {
        return localDataSource.getAllStoredMealPlan();
    }
}
