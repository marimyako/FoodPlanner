package com.example.foodplanner.home.presenter;

import com.example.foodplanner.Model.Meal;

public interface HonePresenterInterface {
    void getRandomMeal();
    void getCategoriesList();

    void getCountriesList();

    void addToFav(Meal meal);

    void addToPlan(String nameofday);
}
