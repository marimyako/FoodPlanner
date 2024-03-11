package com.example.foodplanner;

import android.view.View;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

public interface OnClick {

    void onFavClick(Meal meal);
    void onFavClickcategory(Category category);

    void onDelClick(Meal meal);

    void onPlanClick(MealPlan mealPlan);
    void onPlandelClick(MealPlan mealPlan);
}
