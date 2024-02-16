package com.example.foodplanner.Network;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.IngrdientResponse;
import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Query;

public interface RemoteDataSource {
    Observable<MealResponse> getRandomMeals ();

    Observable<CategoryResponse> getAllCategories();

    Observable<MealResponse> getAllCountries();

    Observable <MealResponse>getCategoryMeals(String category);

    Observable<MealResponse> getCountryMeals(String country);
    Observable <MealResponse> getMealsByName(String name);

    Observable<IngrdientResponse> getAllIngrdient();

    Observable<MealResponse> getMealsByChar(String name);

    Observable<MealResponse> getMealsOfCategory(String category);

    Observable<MealResponse> getMealsOfCountries(String country);

    Observable<MealResponse> getMealsoFIngrdients(String ingredient);



}
