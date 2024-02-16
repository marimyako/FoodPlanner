package com.example.foodplanner.country.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.R;
import com.example.foodplanner.category.view.CategoryScreen;
import com.example.foodplanner.country.presenter.Countrypresenter;


import java.util.ArrayList;
import java.util.List;

public class CountryScreen extends AppCompatActivity implements CountryViewInterface {
    RecyclerView countrydetailrv;
    TextView countrytagtv;
    CountryDetailAdapter countryDetailAdapter;
    Countrypresenter countrypresenter;
    GridLayoutManager gridLayoutManager;

    String countrymeals;

    Meal model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_screen);
        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            countrymeals= extra.getString("CON_MEAL");
        }
        countrydetailrv = findViewById(R.id.mealscountryRV);
        countrytagtv = findViewById(R.id.countrytagTV);
        gridLayoutManager = new GridLayoutManager(this, 2);
        countrypresenter = new Countrypresenter (Repository.getInstance(Connection.getInstance(this), CountryScreen.this, LocalDataSource.getInstance(this)),this);
        countrypresenter.getMeals(countrymeals);
        countryDetailAdapter = new CountryDetailAdapter(this, new ArrayList<>());
        countrydetailrv.setAdapter(countryDetailAdapter);
        countrydetailrv.setLayoutManager(gridLayoutManager);
        countrytagtv.setText(countrymeals);
    }


    @Override
    public void ViewCountryMeal(List<Meal> models) {
        model=models.get(0);
        countryDetailAdapter.setCountryMealModelList(models);
        countryDetailAdapter.notifyDataSetChanged();
    }
}