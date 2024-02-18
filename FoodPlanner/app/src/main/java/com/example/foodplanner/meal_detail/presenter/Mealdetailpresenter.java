package com.example.foodplanner.meal_detail.presenter;

import android.util.Log;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.meal_detail.view.MealdetailViewInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Mealdetailpresenter implements MealdetailpresenterInterface {

    MealdetailViewInterface mealdetailViewInterface;
    Repository repository;
    OnClick onClick;
    private static final String TAG = "Mealdetailpresenter";

    public Mealdetailpresenter(MealdetailViewInterface mealdetailViewInterface, Repository repository, OnClick onClick) {
        this.mealdetailViewInterface = mealdetailViewInterface;
        this.repository = repository;
        this.onClick = onClick;
    }

    @Override
    public void getMealdetail(String MealDetail) {
        Observable<MealResponse> observable = repository.getMealsByName(MealDetail);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        mealdetailViewInterface.ViewMealDetail(mealResponse.getMealsModel());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addToFav(Meal meal) {
        Completable completable = repository.insertmeal(meal);
        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    mealdetailViewInterface.showerror("Meal added to favorites successfully");

                }, error -> {
                    mealdetailViewInterface.showerror("Error adding Meal to favorites");
                });
    }

    @Override
    public void removefav(Meal meal) {
        repository.deletemeal(meal).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                    Log.i(TAG, "Deleted successfully");
                },
                throwable -> {
                    Log.i(TAG, "erorr of deleting");
                }
        );
    }

    @Override
    public void addToPlan(MealPlan mealPlan) {
        Log.i(TAG, "addToPlan: beforeeeeee");
        Completable completable = repository.insertmealplan(mealPlan);
        if (completable != null) {
            completable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        Log.i(TAG, "addToPlan: Successfully Insert");
                    }, error -> {
                        Log.i(TAG, "addToPlan: Failed To Insert");
                    });
        } else {
            Log.e(TAG, "addToPlan: Completable is null");
        }
    }
}

