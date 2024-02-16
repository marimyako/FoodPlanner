package com.example.foodplanner.meal_detail.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.meal_detail.view.MealdetailViewInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Mealdetailpresenter implements MealdetailpresenterInterface{

    MealdetailViewInterface mealdetailViewInterface;
    Repository repository;

    public Mealdetailpresenter(MealdetailViewInterface mealdetailViewInterface, Repository repository) {
        this.mealdetailViewInterface = mealdetailViewInterface;
        this.repository = repository;
    }

    @Override
    public void getMealdetail(String MealDetail) {
        Observable<MealResponse> observable= repository.getMealsByName(MealDetail);
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

}
