package com.example.foodplanner.DB;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.MealPlan.view.Plan;
import com.example.foodplanner.Model.MealPlan;

@androidx.room.Database(entities = {Meal.class, MealPlan.class},version = 2)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance=null;
    public abstract DAO getMealDAO();
    public abstract PlanDAO getPlanDAO();

    public static synchronized DataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),DataBase.class,"mealdb").build();
        }
     return instance ;
    }
}
