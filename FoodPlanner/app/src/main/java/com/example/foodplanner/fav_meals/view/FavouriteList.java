package com.example.foodplanner.fav_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.R;
import com.example.foodplanner.fav_meals.presenter.Favpresenter;
import com.example.foodplanner.fav_meals.presenter.FavpresenterInterface;

import java.util.ArrayList;
import java.util.List;


public class FavouriteList extends Fragment implements FavViewInterface , OnClick {

    RecyclerView favrv;

    FavCardAdapter favCardAdapter;

    RecyclerView.LayoutManager layoutManager;

    LocalDataSource localDataSource;

    Favpresenter favpresenter;
    FavpresenterInterface favpresenterInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    List<Meal> fav=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favrv = view.findViewById(R.id.favrecycler);
        layoutManager = new LinearLayoutManager(getContext());
        favrv.setLayoutManager(layoutManager);
        favCardAdapter = new FavCardAdapter(fav,getContext(), this);
        favrv.setAdapter(favCardAdapter);
        favpresenter = new Favpresenter(Repository.getInstance(Connection.getInstance(getContext()), view.getContext(), LocalDataSource.getInstance(getContext())), this);
        favpresenter.getMeals();

    }

    @Override
    public void delelteFav(Meal meal) {
        favpresenter.removefav(meal);
    }

    @Override
    public void showdata(List<Meal> meals) {
        favCardAdapter.setMealList(meals);
        favCardAdapter.notifyDataSetChanged();
    }


    @Override
    public void showerror(String errormsg) {

    }

    @Override
    public void onFavClick(Meal meal) {
    showdata((List<Meal>) meal);
    }

    @Override
    public void onDelClick(Meal meal) {
        delelteFav(meal);
    }
}