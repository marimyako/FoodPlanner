package com.example.foodplanner.home.view;

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
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.R;
import com.example.foodplanner.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment implements HomeViewInterface , OnClick {

    RecyclerView randommealrecyclerview;
    RecyclerView categoryrecyclerview;

    RecyclerView countryrecyelerview;
    HomePresenter homePresenter;
    LinearLayoutManager mealLayoutManger,categorylayoutmanger,countrylayoutmanger;
    CategoryAdapter categoryAdapter;

    CountryAdapter countryAdapter;
    ViewAdapter viewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        mealLayoutManger=new LinearLayoutManager(view.getContext());
        viewAdapter=new ViewAdapter(view.getContext(),new ArrayList<>(),this);
        randommealrecyclerview.setLayoutManager(mealLayoutManger);
        randommealrecyclerview.setAdapter(viewAdapter);
        randommealrecyclerview.setHasFixedSize(true);
        mealLayoutManger.setOrientation(RecyclerView.HORIZONTAL);
        homePresenter=new HomePresenter(this, Repository.getInstance(Connection.getInstance(getContext()),view.getContext(), LocalDataSource.getInstance(getContext())));
        homePresenter.getRandomMeal();

        categorylayoutmanger=new LinearLayoutManager(view.getContext());
        categoryAdapter=new CategoryAdapter(view.getContext(),new ArrayList<>());
        categoryrecyclerview.setLayoutManager(categorylayoutmanger);
        categoryrecyclerview.setAdapter(categoryAdapter);
        categorylayoutmanger.setOrientation(RecyclerView.HORIZONTAL);
        homePresenter.getCategoriesList();

        countrylayoutmanger=new LinearLayoutManager(view.getContext());
        countryAdapter=new CountryAdapter(view.getContext(),new ArrayList<>());
        countryrecyelerview.setLayoutManager(countrylayoutmanger);
        countryrecyelerview.setAdapter(countryAdapter);
        countrylayoutmanger.setOrientation(RecyclerView.HORIZONTAL);
        homePresenter.getCountriesList();


    }

    private void initUI(View view){
        randommealrecyclerview=view.findViewById(R.id.viewPager);
        categoryrecyclerview=view.findViewById(R.id.categoryRecycler);
        countryrecyelerview=view.findViewById(R.id.countryRecycler);
    }
    @Override
    public void ViewRandomMeal(List<Meal> models) {
          viewAdapter.setViewPagerAdepterList(models);
          viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void ViewAllCategory(List<Category> models) {
     categoryAdapter.setCategoryList(models);
     categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void ViewAllCountries(List<Meal> models) {
        countryAdapter.setContryList(models);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showerror(String error) {

    }

    @Override
    public void addMeal(Meal meal) {
    homePresenter.addToFav(meal);
    }



    @Override
    public void onFavClick(Meal meal) {
        addMeal(meal);
    }

    @Override
    public void  onDelClick(Meal meal) {

    }
}