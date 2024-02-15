package com.example.foodplanner.category.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Model.RepositoryInterface;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.category.view.CategoryViewInterface;

import java.util.List;

public class Categorypresenter implements Categorypresenterinterface, NetworkCallBack {
    RepositoryInterface _repo;

    public Categorypresenter( CategoryViewInterface _view,RepositoryInterface _repo) {
        this._repo = _repo;
        this._view = _view;
    }

    CategoryViewInterface _view;
    @Override
    public void getMeals(String categories) {
        _repo.getCategoryMeals(this,categories);
    }

    @Override
    public void onSuccessResultRandom(List<Meal> meals) {
        _view.ViewCategoriesMeal(meals);
    }

    @Override
    public void onSuccessResultCategories(List<Category> category) {

    }

    @Override
    public void onSuccessResultCountries(List<Meal> meal) {

    }


    @Override
    public void onFailureResult(String errormsg) {
   System.out.println("error categoryyyymeals ");
    }
}
