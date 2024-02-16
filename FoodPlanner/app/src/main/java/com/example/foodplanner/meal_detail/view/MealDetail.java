package com.example.foodplanner.meal_detail.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Ingrdient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.R;
import com.example.foodplanner.category.view.CategoryScreen;
import com.example.foodplanner.meal_detail.presenter.Mealdetailpresenter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetail extends AppCompatActivity implements MealdetailViewInterface {
    private static final String TAG = "MealDetail";
TextView mealname;
TextView mealcountry;
ImageView mealimage;
ImageView addbtn;
ImageView delbtn;
RecyclerView ingrdentrv;
TextView mealsteps;
YouTubePlayerView mealvideo;
Meal_cardAdapter mealCardAdapter;

GridLayoutManager gridLayoutManager;

Mealdetailpresenter mealdetailpresenter;

String mealName;

String[] videoSplit;
String vId;

Meal model;

    List<Ingrdient> ingredient= new ArrayList<Ingrdient>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            mealName = extra.getString("MEAL_NAME");
        }
        Log.i(TAG, "onCreate: "+mealName);
        mealname=findViewById(R.id.mealname);
        mealcountry=findViewById(R.id.mealcountry);
        mealimage=findViewById(R.id.mealimage);
        addbtn=findViewById(R.id.add);
        delbtn=findViewById(R.id.delete);
        ingrdentrv=findViewById(R.id.ingrdentsrv);
        mealsteps=findViewById(R.id.mealsteps);
        mealvideo=findViewById(R.id.mealvideo);
        mealdetailpresenter=new Mealdetailpresenter(this, Repository.getInstance(Connection.getInstance(this), MealDetail.this, LocalDataSource.getInstance(this)));
        mealdetailpresenter.getMealdetail(mealName);
        Log.i(TAG, "onCreate: "+mealName);


    }

    @Override
    public void ViewMealDetail(List<Meal> meal) {
        model=meal.get(0);
        mealname.setText(model.getStrMeal());
        mealcountry.setText(model.getStrArea());
        if(!model.getStrYoutube().equals(""))
        {
            videoSplit =model.getStrYoutube().split("=");
            vId =videoSplit[1];
        }
        getLifecycle().addObserver(mealvideo);

        mealvideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(vId, 0);
            }
        });
        mealsteps.setText(model.getStrInstructions());
        Glide.with(this).load(model.getStrMealThumb()).into(mealimage);
        gridLayoutManager=new GridLayoutManager(this,2);
        mealCardAdapter=new Meal_cardAdapter(this,ingredient);
        String[] ingredients = {
                model.getStrIngredient1(),
                model.getStrIngredient2(),
                model.getStrIngredient3(),
                model.getStrIngredient4(),
                model.getStrIngredient5(),
                model.getStrIngredient6(),
                model.getStrIngredient7(),
                model.getStrIngredient8(),
                model.getStrIngredient9(),
                model.getStrIngredient10(),
                model.getStrIngredient11(),
                model.getStrIngredient12()
        };

        for (String ingredientName : ingredients) {
            if (!ingredientName.isEmpty()) {
                ingredient.add(new Ingrdient(ingredientName, "https://www.themealdb.com/images/ingredients/" + ingredientName + ".png"));
            }
        }

        ingrdentrv.setAdapter(mealCardAdapter);
        ingrdentrv.setLayoutManager(gridLayoutManager);
     //   mealCardAdapter.setMealList(ingrdient);
        mealCardAdapter.notifyDataSetChanged();
    }
}