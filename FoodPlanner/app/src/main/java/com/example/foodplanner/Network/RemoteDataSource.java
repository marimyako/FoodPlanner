package com.example.foodplanner.Network;

public interface RemoteDataSource {
    void getRandomMeals (NetworkCallBack networkCallBack);

    void getAllCategories(NetworkCallBack networkCallBack);

    void getAllCountries(NetworkCallBack networkCallBack);

    void getCategoryMeals(NetworkCallBack networkCallBack,String category);

    void getCountryMeals(NetworkCallBack networkCallBack,String country);
    void  getMealsByName(NetworkCallBack networkCallBack,String name);

}
