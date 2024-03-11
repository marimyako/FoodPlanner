package com.example.foodplanner.DB;

import android.content.Context;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalDataSource implements LocalDataSourceInterface{

    Context context;
    private DAO dao;
    private PlanDAO planDAO;
    private Flowable<List<Meal>> storedMeal;

    public LocalDataSource(Context context) {
        DataBase db= DataBase.getInstance(context.getApplicationContext());
        dao= db.getMealDAO();
        planDAO=db.getPlanDAO();

    }

    public static synchronized LocalDataSource getInstance(Context context){
        if(localDataSource==null) {
            localDataSource= new LocalDataSource(context);
        }

        return localDataSource;
    }

    private static LocalDataSource localDataSource=null;
    @Override
    public Completable insertmeal(Meal meal) {
        return dao.insertmeal(meal).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deletemeal(Meal meal) {
        return  dao.deletemeal(meal).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Meal>> getAllStoredMeal() {
        return  dao.getMeals().subscribeOn(Schedulers.io());
    }

    @Override
    public Completable insertmealplan(MealPlan mealPlan) {
        return planDAO.insertmealplan(mealPlan);
    }

    @Override
    public Completable deletemealplan(MealPlan mealPlan) {
        return planDAO.deletemealplan(mealPlan).subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<MealPlan>> getAllStoredMealPlan() {
        return planDAO.getMeals();

    }
}
