package com.example.foodplanner.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.Meal;

@androidx.room.Database(entities = {Meal.class},version = 1)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance=null;
    public abstract DAO getMealDAO();

    public static synchronized DataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),DataBase.class,"mealdb").build();
        }
     return instance ;
    }
}
