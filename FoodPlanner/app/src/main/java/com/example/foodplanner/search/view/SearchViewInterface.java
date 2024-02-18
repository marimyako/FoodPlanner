package com.example.foodplanner.search.view;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingrdient;
import com.example.foodplanner.Model.IngrdientDTO;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface SearchViewInterface {

    void getAllCategories(List<Category> categories);

     void getAllCountries(List<Country> countries);

    void showerror(String  error);


    void addToFavorite (Meal mealModel);
}
