package com.example.foodplanner;

import android.view.View;

import com.example.foodplanner.Model.Meal;

public interface OnClick {

    void onFavClick(Meal meal);

    void onDelClick(Meal meal);
}
