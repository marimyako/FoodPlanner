package com.example.foodplanner.country.presenter;

import com.example.foodplanner.Model.Meal;

public interface Countrypresenterinterface {

    void getMeals(String countries);

    void addToFav(Meal meal);
}
