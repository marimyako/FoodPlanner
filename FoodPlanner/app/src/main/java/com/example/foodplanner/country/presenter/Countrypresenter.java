package com.example.foodplanner.country.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.RepositoryInterface;
import com.example.foodplanner.Network.NetworkCallBack;
import com.example.foodplanner.country.view.CountryViewInterface;

import java.util.List;

public class Countrypresenter implements NetworkCallBack,Countrypresenterinterface {
    RepositoryInterface _repo;

    public Countrypresenter(RepositoryInterface _repo, CountryViewInterface countryViewInterface) {
        this._repo = _repo;
        this.countryViewInterface = countryViewInterface;
    }

    CountryViewInterface countryViewInterface;
    @Override
    public void onSuccessResultRandom(List<Meal> meals) {
      countryViewInterface.ViewCountryMeal(meals);
    }

    @Override
    public void onSuccessResultCategories(List<Category> category) {

    }

    @Override
    public void onSuccessResultCountries(List<Meal> meal) {

    }

    @Override
    public void onFailureResult(String errormsg) {

    }

    @Override
    public void getMeals(String countries) {
       _repo.getCountryMeals(this,countries);
    }
}
