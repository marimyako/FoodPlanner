package com.example.foodplanner.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DAO {

    @Query("SELECT * FROM meal_table")
    Flowable<List<Meal>> getMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertmeal(Meal meal);

    @Delete
    Completable deletemeal(Meal meal);
}
