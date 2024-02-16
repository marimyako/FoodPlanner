package com.example.foodplanner.meal_detail.presenter;

import com.example.foodplanner.Model.Meal;

public interface MealdetailpresenterInterface {
    void getMealdetail(String MealDetail);

    void addToFav(Meal meal);
    void  removefav(Meal meal);
}
