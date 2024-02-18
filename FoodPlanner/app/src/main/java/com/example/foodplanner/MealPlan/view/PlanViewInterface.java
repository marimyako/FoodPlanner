package com.example.foodplanner.MealPlan.view;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

import java.util.List;

public interface PlanViewInterface {

    void deleltemealplan(MealPlan mealPlan);
    void showdatamealplan();
    void showerrormealplan(String errormsg);
}
