package com.example.foodplanner.category.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.RepositoryInterface;
import com.example.foodplanner.category.view.CategoryViewInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Categorypresenter implements Categorypresenterinterface{
    RepositoryInterface _repo;

    public Categorypresenter( CategoryViewInterface _view,RepositoryInterface _repo) {
        this._repo = _repo;
        this._view = _view;
    }

    CategoryViewInterface _view;
    @Override
    public void getMeals(String categories) {
        Observable<MealResponse> observable= _repo.getCategoryMeals(categories);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.ViewCategoriesMeal(mealResponse.getMealsModel());
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
        Completable completable=_repo.insertmeal(meal);
        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    _view.showerror("Meal added to favorites successfully");

                }, error -> {
                    _view.showerror("Error adding Meal to favorites");});
    }
}
