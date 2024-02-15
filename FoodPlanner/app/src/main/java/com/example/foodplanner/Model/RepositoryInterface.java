package com.example.foodplanner.Model;

import com.example.foodplanner.Network.NetworkCallBack;

public interface RepositoryInterface {
    void getRandomMeal(NetworkCallBack networkCallBack);

    void getAllCategories(NetworkCallBack networkCallBack);

    void getAllCountries(NetworkCallBack networkCallBack);

    void getCategoryMeals(NetworkCallBack networkCallBack,String category);

    void getCountryMeals(NetworkCallBack networkCallBack,String country);
    void getMealsByName(NetworkCallBack networkCallBack,String name);
}
