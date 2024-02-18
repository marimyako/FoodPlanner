package com.example.foodplanner.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface PlanDAO {
    @Query("SELECT * FROM plan_table")
    Flowable<List<MealPlan>> getMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertmealplan(MealPlan mealPlan);

    @Delete
    Completable deletemealplan(MealPlan mealPlan);
}
