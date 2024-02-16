package com.example.foodplanner.category.presenter;

import com.example.foodplanner.Model.Meal;

public interface Categorypresenterinterface {

    void getMeals(String categories);

    void addToFav(Meal meal);
}
