package com.example.foodplanner.DB;

import android.content.Context;

import androidx.room.Database;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalDataSource implements LocalDataSourceInterface{

    Context context;
    private DAO dao;
    private Flowable<List<Meal>> storedMeal;

    public LocalDataSource(Context context) {
        DataBase db= DataBase.getInstance(context.getApplicationContext());
        dao= db.getMealDAO();
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
}
