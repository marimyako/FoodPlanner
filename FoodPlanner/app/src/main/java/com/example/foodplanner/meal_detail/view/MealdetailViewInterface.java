package com.example.foodplanner.meal_detail.view;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

import java.util.List;

public interface MealdetailViewInterface {
    void ViewMealDetail(List<Meal> meal);
    void ViewMealPlanDetail(List<MealPlan> mealPlans);

    void showerror(String  error);

    void addMeal(Meal meal);

    void delelteFav(Meal meal);

    void addToPlanmeal(MealPlan mealPlan);
}
