package com.example.foodplanner.meal_detail.presenter;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

public interface MealdetailpresenterInterface {
    void getMealdetail(String MealDetail);
    void getMealPlandetail(String MealPlanDetail);


    void addToFav(Meal meal);
    void  removefav(Meal meal);

    void  addToPlan(MealPlan mealPlan);
}
