package com.example.foodplanner.country.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.RepositoryInterface;
import com.example.foodplanner.country.view.CountryViewInterface;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Countrypresenter implements Countrypresenterinterface {
    RepositoryInterface _repo;

    public Countrypresenter(RepositoryInterface _repo, CountryViewInterface countryViewInterface) {
        this._repo = _repo;
        this.countryViewInterface = countryViewInterface;
    }

    CountryViewInterface countryViewInterface;


    @Override
    public void getMeals(String countries) {
        Observable<MealResponse>observable= _repo.getCountryMeals(countries);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        countryViewInterface.ViewCountryMeal(mealResponse.getMealsModel());
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
