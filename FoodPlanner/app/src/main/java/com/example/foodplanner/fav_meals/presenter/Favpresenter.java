package com.example.foodplanner.fav_meals.presenter;

import android.util.Log;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.fav_meals.view.FavViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Favpresenter implements FavpresenterInterface {
    private static final String TAG = "Favpresenter";
    Repository repository;

    public Favpresenter(Repository repository, FavViewInterface favViewInterface) {
        this.repository = repository;
        this.favViewInterface = favViewInterface;
    }

    FavViewInterface favViewInterface;

    @Override
    public void getMeals() {
        repository.getStoredMeals().observeOn(AndroidSchedulers.mainThread()).subscribe(meals -> {
           favViewInterface.showdata(meals);
        });
    }

    @Override
    public void removefav(Meal meal) {
         repository.deletemeal(meal).observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{
                     Log.i(TAG, "Deleted successfully");},
                 throwable -> {
                     Log.i(TAG, "erorr of deleting");
                 }
                 );
    }
}
