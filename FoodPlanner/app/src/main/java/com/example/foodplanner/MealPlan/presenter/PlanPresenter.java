package com.example.foodplanner.MealPlan.presenter;

import android.util.Log;

import com.example.foodplanner.MealPlan.view.PlanViewInterface;
import com.example.foodplanner.Model.MealPlan;
import com.example.foodplanner.Model.Repository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;

public class PlanPresenter implements PlanPresenterInterface{
    private static final String TAG = "PlanPresenter";
    Repository repository;

    public PlanPresenter(Repository repository, PlanViewInterface planViewInterface) {
        this.repository = repository;
        this.planViewInterface = planViewInterface;
    }

    PlanViewInterface planViewInterface;
    @Override
    public  Flowable<List<MealPlan>> getMealplan() {
       return repository.getStoredMealsplan();

    }

    @Override
    public void removemealplan(MealPlan mealPlan) {
      repository.deletemealplan(mealPlan).observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{
                  Log.i(TAG, "Deleted successfully");},
              throwable -> {
                  Log.i(TAG, "erorr of deleting");
              }
      );
    }
}
