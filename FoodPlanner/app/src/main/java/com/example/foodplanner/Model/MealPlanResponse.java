package com.example.foodplanner.Model;

import java.util.List;

public class MealPlanResponse {
    private List<MealPlan> mealPlans;
    public MealPlanResponse(List<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
    public List<MealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMeals(List<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
}
