package com.example.foodplanner.MealPlan.presenter;


import com.example.foodplanner.Model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlanPresenterInterface {
    public Flowable<List<MealPlan>> getMealplan();
    public void  removemealplan(MealPlan mealPlan);
}
