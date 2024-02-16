package com.example.foodplanner.category.view;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface CategoryViewInterface
{
    void ViewCategoriesMeal(List<Meal> models);

    void showerror(String  error);
    void addMeal(Meal meal);
}
