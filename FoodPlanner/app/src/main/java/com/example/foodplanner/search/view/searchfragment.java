package com.example.foodplanner.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.R;
import com.example.foodplanner.search.presenter.SearchPresenter;
import com.example.foodplanner.search.presenter.SearchPresenterInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class searchfragment extends Fragment implements SearchOnClick,SearchViewInterface{
    RecyclerView recyclerView;

    SearchByCountryAdapter searchByCountryAdapter;
    SearchByCategoryAdapter searchByCategoryAdapter;
    //SearchByMealNameAdapter searchByMealNameAdapter ;

    SearchPresenterInterface searchPresenterInterface;

    GridLayoutManager layoutManager;
    List<Meal> mealModels ;
    List<Country> countries ;
    List<Category> categories ;
    RadioButton category;
    RadioButton country;


    SearchView searchView;

    RadioGroup group ;

    boolean CategoryFlag = false;
    boolean CountryFlag = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerSearch);
        category = view.findViewById(R.id.radioCategory);
        country = view.findViewById(R.id.radioCountry);
        group = view.findViewById(R.id.groubSearch);
        searchView = view.findViewById(R.id.search);
        searchPresenterInterface = new SearchPresenter(  Repository.getInstance(Connection.getInstance(getContext()),view.getContext(), LocalDataSource.getInstance(getContext())),searchfragment.this);
        layoutManager=new GridLayoutManager(getContext(),2);
        searchByCountryAdapter=new SearchByCountryAdapter(getContext(),countries,this);
        searchByCategoryAdapter=new SearchByCategoryAdapter(getContext(),categories,this);
        recyclerView.setLayoutManager(layoutManager);
        category.setOnClickListener(event -> {
            if (CategoryFlag == false) {
                categoryOnClick();
                CategoryFlag = true;
            }else{
                CategoryFlag = false;
                recyclerView.setAdapter(null);
                group.clearCheck();
                categories = null;
                countries = null;
            }
        });
        country.setOnClickListener(event -> {
            if (CountryFlag == false) {
                countryOnClick();
                CountryFlag = true;
            }else{
                CountryFlag = false;
                recyclerView.setAdapter(null);
                group.clearCheck();
                countries = null;
                categories = null;}
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchView.getQuery().toString().equals("") && group.getCheckedRadioButtonId() == -1){
                 ;
                    recyclerView.setAdapter(null);
                    categories = null;
                    countries = null;

                }

                 if(countries != null) {
                    List<Country> result = new ArrayList<>();
                    Observable<Country> observable = Observable.fromIterable(countries);
                    observable.filter(e -> e.getStrArea().contains(newText)).subscribe(i -> result.add(i));
                    recyclerView.setAdapter(searchByCountryAdapter);
                    searchByCountryAdapter.setContryList(result);
                    searchByCountryAdapter.notifyDataSetChanged();
                }

                else if(categories != null) {
                    List<Category> result = new ArrayList<>();
                    Observable<Category> observable = Observable.fromIterable(categories);
                    observable.filter(e -> e.getStrCategory().contains(newText)).subscribe(i -> result.add(i));
                    recyclerView.setAdapter(searchByCategoryAdapter);
                    searchByCategoryAdapter.setCategoryList(result);
                    searchByCategoryAdapter.notifyDataSetChanged();
                }

                return false;
            }
        });

    }

    @Override
    public void categoryOnClick() {
searchPresenterInterface.getAllCategories();
    }

    @Override
    public void countryOnClick() {
searchPresenterInterface.getAllCountries();
    }


    @Override
    public void addToFavoriteOnClick(Meal mealModel) {
searchPresenterInterface.addToFavorite(mealModel);
    }



    @Override
    public void getAllCategories(List<Category> categories) {
recyclerView.setAdapter(searchByCategoryAdapter);
this.categories=categories;
searchByCategoryAdapter.setCategoryList(categories);
searchByCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllCountries(List<Country> countries) {
recyclerView.setAdapter(searchByCountryAdapter);
this.countries=countries;
searchByCountryAdapter.setContryList(countries);
searchByCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showerror(String error) {
searchPresenterInterface.showerror(error);
    }




    @Override
    public void addToFavorite(Meal mealModel) {
searchPresenterInterface.addToFavorite(mealModel);
    }
}