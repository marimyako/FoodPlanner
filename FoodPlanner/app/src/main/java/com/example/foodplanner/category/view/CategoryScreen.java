package com.example.foodplanner.category.view;

import static java.security.AccessController.getContext;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.R;
import com.example.foodplanner.category.presenter.Categorypresenter;
import com.example.foodplanner.category.presenter.Categorypresenterinterface;


import java.util.ArrayList;
import java.util.List;

public class CategoryScreen extends AppCompatActivity implements CategoryViewInterface {

    RecyclerView categorydetailrv;
    TextView categorytagtv;
    CategotyDetailAdapter categotyDetailAdapter;
    Categorypresenter categorypresenter;
    GridLayoutManager gridLayoutManager;

    String categorymeals;

    Meal model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            categorymeals= extra.getString("CAT_MEAL");
        }
        categorydetailrv = findViewById(R.id.mealscategoryRV);
        categorytagtv = findViewById(R.id.categorytagTV);
        gridLayoutManager = new GridLayoutManager(this, 2);
        categorypresenter = new Categorypresenter(this, Repository.getInstance(Connection.getInstance(this), CategoryScreen.this,LocalDataSource.getInstance(this)));
        categorypresenter.getMeals(categorymeals);
        categotyDetailAdapter = new CategotyDetailAdapter(this, new ArrayList<>());
        categorydetailrv.setAdapter(categotyDetailAdapter);
        categorydetailrv.setLayoutManager(gridLayoutManager);
        categorytagtv.setText(categorymeals);

    }

    @Override
    public void ViewCategoriesMeal(List<Meal> meals) {
        model=meals.get(0);
        categotyDetailAdapter.setCategoriesMealModelList(meals);
        categotyDetailAdapter.notifyDataSetChanged();
    }
}