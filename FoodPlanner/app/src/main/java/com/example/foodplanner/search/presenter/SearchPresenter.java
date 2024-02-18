package com.example.foodplanner.search.presenter;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.IngrdientResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.search.view.SearchViewInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchPresenter implements SearchPresenterInterface{

    Repository repository;

    SearchViewInterface searchViewInterface;

    public SearchPresenter(Repository repository, SearchViewInterface searchViewInterface) {
        this.repository = repository;
        this.searchViewInterface = searchViewInterface;
    }

    @Override
    public void getAllCategories() {
repository.getAllCategories().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CategoryResponse>() {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull CategoryResponse categoryResponse) {
        searchViewInterface.getAllCategories(categoryResponse.getCategories());
    }

    @Override
    public void onError(@NonNull Throwable e) {
        searchViewInterface.showerror(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
});
    }
    @Override
    public void addToFavorite(Meal meal) {
        Completable completable=repository.insertmeal(meal);
        completable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    searchViewInterface.showerror("Meal added to favorites successfully");

                }, error -> {
                    searchViewInterface.showerror("Error adding Meal to favorites");});

    }

    @Override
    public void getAllCountries() {
  repository.getFullofCountries().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CountryResponse>() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {

      }

      @Override
      public void onNext(@NonNull CountryResponse countryResponse) {
         searchViewInterface.getAllCountries(countryResponse.getCountries());
      }

      @Override
      public void onError(@NonNull Throwable e) {
          searchViewInterface.showerror(e.getMessage());
      }

      @Override
      public void onComplete() {

      }
  });
    }


    @Override
    public void showerror(String error) {
        searchViewInterface.showerror(error);
    }
}
