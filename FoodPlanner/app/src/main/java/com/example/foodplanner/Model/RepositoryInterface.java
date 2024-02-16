package com.example.foodplanner.Model;

import com.airbnb.lottie.L;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    Observable<MealResponse> getRandomMeal();

    Observable<CategoryResponse> getAllCategories();

    Observable<MealResponse> getAllCountries();

    Observable<MealResponse> getCategoryMeals(String category);

    Observable<MealResponse> getCountryMeals(String country);
    Observable<MealResponse> getMealsByName(String name);

    Observable<IngrdientResponse> getAllIngrdient();

    Observable<MealResponse> getMealsByChar(String name);

    Observable<MealResponse> getMealsOfCategory(String category);

    Observable<MealResponse> getMealsOfCountries(String country);

    Observable<MealResponse> getMealsoFIngrdients(String ingredient);

    Completable insertmeal (Meal meal);
    Completable deletemeal(Meal meal);
    Flowable<List<Meal>> getStoredMeals();
}
