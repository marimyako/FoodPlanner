package com.example.foodplanner.meal_detail.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.meal_detail.view.MealdetailViewInterface;

import java.util.List;

public class Mealdetailpresenter implements MealdetailpresenterInterface, NetworkCallBack {

    MealdetailViewInterface mealdetailViewInterface;
    Repository repository;

    public Mealdetailpresenter(MealdetailViewInterface mealdetailViewInterface, Repository repository) {
        this.mealdetailViewInterface = mealdetailViewInterface;
        this.repository = repository;
    }

    @Override
    public void getMealdetail(String MealDetail) {
       repository.getMealsByName(this,MealDetail);
    }

    @Override
    public void onSuccessResultRandom(List<Meal> meals) {
        mealdetailViewInterface.ViewMealDetail(meals);
    }

    @Override
    public void onSuccessResultCategories(List<Category> category) {

    }

    @Override
    public void onSuccessResultCountries(List<Meal> meal) {

    }


    @Override
    public void onFailureResult(String errormsg) {
        System.out.println("error offffffffffmeals ");
    }
}
