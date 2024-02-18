package com.example.foodplanner.fav_meals.view;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface FavViewInterface {

    void delelteFav(Meal meal);
    void showdata(List<Meal> meals);
    void showerror(String errormsg);
}
