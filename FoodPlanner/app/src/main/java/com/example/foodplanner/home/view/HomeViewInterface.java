package com.example.foodplanner.home.view;

import com.airbnb.lottie.L;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface HomeViewInterface {
    void ViewRandomMeal(List<Meal> models);
    void ViewAllCategory(List<Category>models);

    void ViewAllCountries(List<Meal>models);

    void showerror(String  error);


    public void addProuduct(Meal meal);

}
