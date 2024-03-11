package com.example.foodplanner.Network;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.IngrdientResponse;
import com.example.foodplanner.Model.MealPlanResponse;
import com.example.foodplanner.Model.MealResponse;

import java.util.Locale;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
   @GET("random.php")
   Observable<MealResponse> getRandomMeals();

   @GET("categories.php")
   Observable<CategoryResponse> getAllCategories();

   @GET("list.php")
   Observable<MealResponse> getAllCountries(@Query("a") String country);

   @GET("filter.php")
   Observable<MealResponse> getCategoryMeals(@Query("c") String categoryName);

   @GET("filter.php")
   Observable<MealResponse> getCountryMeals(@Query("a") String categoryName);

   @GET("search.php")
   Observable<MealResponse> getMealsByName(@Query("s") String name);

   @GET("search.php")
   Observable<MealPlanResponse> getMealsPlanByName(@Query("s") String name);
   @GET("list.php?i=list")
   Observable<IngrdientResponse> getAllIngrdient();

   @GET("search.php")
   Observable<MealResponse> getMealsByChar(@Query("f") String name);

   @GET("filter.php")
   Observable<MealResponse> getMealsOfCategory(@Query("c") String category);

   @GET("filter.php")
   Observable<MealResponse> getMealsOfCountries(@Query("a") String country);

   @GET("filter.php")
   Observable<MealResponse> getMealsoFIngrdients(@Query("i") String ingredient);

   @GET("list.php?a=list")
   Observable<CountryResponse> getAllCountries();

}
