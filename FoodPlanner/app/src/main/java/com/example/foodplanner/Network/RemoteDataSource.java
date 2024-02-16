package com.example.foodplanner.Network;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteDataSource {
    Observable<MealResponse> getRandomMeals ();

    Observable<CategoryResponse> getAllCategories();

    Observable<MealResponse> getAllCountries();

    Observable <MealResponse>getCategoryMeals(String category);

    Observable<MealResponse> getCountryMeals(String country);
    Observable <MealResponse> getMealsByName(String name);

}
