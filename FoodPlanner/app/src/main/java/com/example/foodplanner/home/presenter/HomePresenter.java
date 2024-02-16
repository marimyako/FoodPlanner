package com.example.foodplanner.home.presenter;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.home.view.HomeViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenter implements HonePresenterInterface{

   HomeViewInterface _view;
   Repository _repo;


   public HomePresenter(HomeViewInterface view,Repository repo){
       this._view=view;
       this._repo=repo;
   }
    @Override
    public void getRandomMeal() {
       _repo.getRandomMeal().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MealResponse>() {
           @Override
           public void onSubscribe(@NonNull Disposable d) {

           }

           @Override
           public void onNext(@NonNull MealResponse mealResponse) {
               _view.ViewRandomMeal(mealResponse.getMealsModel());
           }

           @Override
           public void onError(@NonNull Throwable e) {
               _view.showerror(e.getMessage());
           }

           @Override
           public void onComplete() {

           }
       });
    }

    @Override
    public void getCategoriesList() {
        _repo.getAllCategories().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CategoryResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull CategoryResponse categoryResponse) {
                _view.ViewAllCategory(categoryResponse.getCategories());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                _view.showerror(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getCountriesList() {
        _repo.getAllCountries().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MealResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MealResponse mealResponse) {
                _view.ViewAllCountries(mealResponse.getMealsModel());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                _view.showerror(e.getMessage());
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
