package com.example.foodplanner.DB;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface LocalDataSourceInterface {


    Completable insertmeal(Meal meal);

    Completable deletemeal(Meal meal);

    Flowable<List<Meal>>  getAllStoredMeal();


    Completable insertmealplan(MealPlan mealPlan);

    Completable deletemealplan(MealPlan mealPlan);

    Flowable<List<MealPlan>>  getAllStoredMealPlan();
}
