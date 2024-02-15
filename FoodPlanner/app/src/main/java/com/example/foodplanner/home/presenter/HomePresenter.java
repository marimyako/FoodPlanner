package com.example.foodplanner.home.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.home.view.HomeViewInterface;

import java.util.List;

public class HomePresenter implements NetworkCallBack ,HonePresenterInterface{

   HomeViewInterface _view;
   Repository _repo;


   public HomePresenter(HomeViewInterface view,Repository repo){
       this._view=view;
       this._repo=repo;
   }
    @Override
    public void onSuccessResultRandom(List<Meal> meals) {
     _view.ViewRandomMeal(meals);
    }

    @Override
    public void onSuccessResultCategories(List<Category> category) {
           _view.ViewAllCategory(category);
    }

    @Override
    public void onSuccessResultCountries(List<Meal> meals) {
        _view.ViewAllCountries(meals);
    }

    @Override
    public void onFailureResult(String errormsg) {
       System.out.println("error when get data in home presenter");
    }

    @Override
    public void getRandomMeal() {
       _repo.getRandomMeal(this);
    }

    @Override
    public void getCategoriesList() {
        _repo.getAllCategories(this);
    }

    @Override
    public void getCountriesList() {
        _repo.getAllCountries(this);
    }

}
