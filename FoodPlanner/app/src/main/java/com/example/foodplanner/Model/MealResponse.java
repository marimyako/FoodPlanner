package com.example.foodplanner.Model;

import java.util.List;

public class MealResponse {
    private List<Meal> meals;
    public MealResponse(List<Meal> mealsModel) {
        this.meals = mealsModel;
    }
    public List<Meal> getMealsModel() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
