package com.example.foodplanner.MealPlan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.DB.LocalDataSourceInterface;
import com.example.foodplanner.MealPlan.presenter.PlanPresenter;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Plan extends Fragment implements PlanViewInterface, OnClick {
RecyclerView  sundayRV;
RecyclerView mondayRV;
RecyclerView tuesdayRV;
RecyclerView wednesdayRV;
RecyclerView thursdayRV;
RecyclerView fridayRV;

RecyclerView saturdayRV;

MealPlanAdapter  saturdayAdapter;
MealPlanAdapter   sundayAdapter;
MealPlanAdapter  mondayAdapter;
MealPlanAdapter  tuesdayAdapter;
MealPlanAdapter   wednesdayAdapter;
MealPlanAdapter thursdayAdapter;
MealPlanAdapter  fridayAdapter;

LocalDataSource localDataSource;

LocalDataSourceInterface  localDataSourceInterface;
PlanViewInterface viewInterface;

PlanPresenter presenter;

LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sundayRV=view.findViewById(R.id.sundayRV);
        mondayRV=view.findViewById(R.id.mondayRV);
        tuesdayRV=view.findViewById(R.id.tuesdayRV);
        wednesdayRV=view.findViewById(R.id.wednesdayRV);
        thursdayRV=view.findViewById(R.id.ThursdayRV);
        fridayRV=view.findViewById(R.id.fridayRV);
        saturdayRV=view.findViewById(R.id.saturdayRV);
        layoutManager=new LinearLayoutManager(view.getContext());
        saturdayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        sundayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        mondayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        tuesdayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        wednesdayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        thursdayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        fridayAdapter=new MealPlanAdapter(view.getContext(),new ArrayList<>(),this);
        LinearLayoutManager saturdayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        saturdayRV.setLayoutManager(saturdayLayoutManager);
        LinearLayoutManager sundayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        sundayRV.setLayoutManager(sundayLayoutManager);
        LinearLayoutManager mondayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mondayRV.setLayoutManager(mondayLayoutManager);
        LinearLayoutManager tuesdayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        tuesdayRV.setLayoutManager(tuesdayLayoutManager);
        LinearLayoutManager wednesdayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        wednesdayRV.setLayoutManager(wednesdayLayoutManager);
        LinearLayoutManager thursdayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        thursdayRV.setLayoutManager(thursdayLayoutManager);
        LinearLayoutManager fridayLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fridayRV.setLayoutManager(fridayLayoutManager);
        sundayRV.setAdapter(sundayAdapter);
        mondayRV.setAdapter( mondayAdapter);
        tuesdayRV.setAdapter( tuesdayAdapter);
        wednesdayRV.setAdapter(wednesdayAdapter);
        thursdayRV.setAdapter(thursdayAdapter);
        fridayRV.setAdapter(fridayAdapter);
        saturdayRV.setAdapter(saturdayAdapter);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        presenter=new PlanPresenter(Repository.getInstance(Connection.getInstance(getContext()),view.getContext(), LocalDataSource.getInstance(getContext())),this);
        showdatamealplan();
    }

    @Override
    public void deleltemealplan(MealPlan mealPlan) {
            presenter.removemealplan(mealPlan);
    }

    private List<MealPlan> filterMealsByDay(List<MealPlan> meals, String day) {
        List<MealPlan> filteredMeals = new ArrayList<>();
        for (MealPlan meal : meals) {
            if (meal.getNameOfday().equalsIgnoreCase(day)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;}
    @Override
    public void showdatamealplan() {

        Flowable<List<MealPlan>> myFavProducts =presenter.getMealplan();
        myFavProducts.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    saturdayAdapter.setMealplanList(filterMealsByDay(meals, "saturday"));
                    sundayAdapter.setMealplanList(filterMealsByDay(meals, "sunday"));
                    mondayAdapter.setMealplanList(filterMealsByDay(meals, "monday"));
                    tuesdayAdapter.setMealplanList(filterMealsByDay(meals, "tuesday"));
                    wednesdayAdapter.setMealplanList(filterMealsByDay(meals, "wednesday"));
                    thursdayAdapter.setMealplanList(filterMealsByDay(meals, "thursday"));
                    fridayAdapter.setMealplanList(filterMealsByDay(meals, "friday"));});
    }

    @Override
    public void showerrormealplan(String errormsg) {
     viewInterface.showerrormealplan(errormsg);
    }

    @Override
    public void onFavClick(Meal meal) {

    }

    @Override
    public void onFavClickcategory(Category category) {

    }

    @Override
    public void onDelClick(Meal meal) {

    }

    @Override
    public void onPlanClick(MealPlan mealPlan) {

    }
}