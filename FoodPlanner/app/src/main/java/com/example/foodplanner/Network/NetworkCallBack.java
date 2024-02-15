package com.example.foodplanner.Network;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkCallBack {

    public void onSuccessResultRandom(List<Meal>meals);
    ////////////////////////////

    public void onSuccessResultCategories(List<Category> category);

    public void onSuccessResultCountries(List<Meal> meal);
    public  void onFailureResult(String errormsg);
}
