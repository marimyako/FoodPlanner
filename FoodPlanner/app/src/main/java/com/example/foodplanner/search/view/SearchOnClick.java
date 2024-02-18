package com.example.foodplanner.search.view;

import com.example.foodplanner.Model.Meal;

public interface SearchOnClick {
  
    void categoryOnClick();
    void countryOnClick();

    void addToFavoriteOnClick(Meal mealModel);
}
