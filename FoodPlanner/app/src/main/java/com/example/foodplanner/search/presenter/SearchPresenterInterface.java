package com.example.foodplanner.search.presenter;

import com.example.foodplanner.Model.Meal;

public interface SearchPresenterInterface {
     void getAllCategories();

     void getAllCountries();

     void addToFavorite(Meal meal);

     void showerror(String error);
}
