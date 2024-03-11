package com.example.foodplanner.meal_detail.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Ingrdient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;
import com.example.foodplanner.Model.Repository;
import com.example.foodplanner.Network.Connection;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_detail.presenter.Mealdetailpresenter;
import com.example.foodplanner.meal_detail.presenter.MealdetailpresenterInterface;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealDetail extends AppCompatActivity implements MealdetailViewInterface , OnClick {
    private static final String TAG = "MealDetail";
TextView mealname;
TextView mealcountry;
ImageView mealimage;
Button addtofav;
RecyclerView ingrdentrv;
TextView mealsteps;
YouTubePlayerView mealvideo;
Meal_cardAdapter mealCardAdapter;

GridLayoutManager gridLayoutManager;

MealdetailpresenterInterface mealdetailpresenter;

String mealName;

String[] videoSplit;
Button addtoplan;

Button removeplan;
String vId;

Meal model;

MealPlan mealPlan;
OnClick onClick=this;



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
        addtoplan=findViewById(R.id.addtoplanbtn);
        mealcountry=findViewById(R.id.mealcountry);
        mealimage=findViewById(R.id.mealimage);
        addtofav=findViewById(R.id.add);
        ingrdentrv=findViewById(R.id.ingrdentsrv);
        mealsteps=findViewById(R.id.mealsteps);
        mealvideo=findViewById(R.id.mealvideo);
        mealdetailpresenter=new Mealdetailpresenter(this, Repository.getInstance(Connection.getInstance(this), MealDetail.this, LocalDataSource.getInstance(this)),this);
        mealdetailpresenter.getMealdetail(mealName);
        mealdetailpresenter.getMealPlandetail(mealName);
        Log.i(TAG, "onCreate: "+mealName);




    }
    private void showDayChooserDialog(final Meal meal) {
        final List<String> daysOfWeek = new ArrayList<>(Arrays.asList(
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        ));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Day")
                .setItems(daysOfWeek.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedDay = daysOfWeek.get(which);
                        Toast.makeText(MealDetail.this, "Selected day: " + selectedDay, Toast.LENGTH_SHORT).show();
                        MealPlan mealPlanObject = new MealPlan(selectedDay, meal.getStrMeal(), meal.getStrMealThumb(), meal.getIdMeal());
                        onPlanClick(mealPlanObject); // Pass the mealPlanObject to the click listener
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();}
    @Override
    public void addMeal(Meal meal) {
        mealdetailpresenter.addToFav(meal);
    }

    @Override
    public void delelteFav(Meal meal) {

    }

    @Override
    public void addToPlanmeal(MealPlan mealPlan) {
        Log.i(TAG, "addToPlanmeal: beffore mealdeatpre");
        mealdetailpresenter.addToPlan(mealPlan);
    }

    @Override
    public void onFavClick(Meal meal) {
        addMeal(meal);
    }

    @Override
    public void onFavClickcategory(Category category) {

    }


    @Override
    public void ViewMealDetail(List<Meal> meal) {
        //  Meal meal1 = null;
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
        mealCardAdapter=new Meal_cardAdapter(this,ingredient,this);
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
                ingredient.add(new Ingrdient(ingredientName));
            }
        }
        addtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onFavClick(model);
                Toast.makeText(MealDetail.this, "Meal Added to your Favourites", Toast.LENGTH_SHORT).show();
            }
        });



        ingrdentrv.setAdapter(mealCardAdapter);
        ingrdentrv.setLayoutManager(gridLayoutManager);
        //   mealCardAdapter.setMealList(ingrdient);
        mealCardAdapter.notifyDataSetChanged();

        mealPlan = new MealPlan( "sunday", model.getIdMeal(),model.getStrMeal(),model.getStrMealThumb());
        addtoplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: meal detbebefore");
                showDayChooserDialog(model);
                Toast.makeText(MealDetail.this, "Meal Added to your Plans", Toast.LENGTH_SHORT).show();
                // onPlanClick(mealPlan);
            }
        });
    }

    @Override
    public void ViewMealPlanDetail(List<MealPlan> mealPlans) {

    }

  /*  @Override
    public void ViewMealPlanDetail(List<MealPlan> mealPlans) {
        mealPlan=mealPlans.get(0);
        mealname.setText( mealPlan.getStrMeal());
        mealcountry.setText( mealPlan.getStrArea());
        if(! mealPlan.getStrYoutube().equals(""))
        {
            videoSplit = mealPlan.getStrYoutube().split("=");
            vId =videoSplit[1];
        }
        getLifecycle().addObserver(mealvideo);

        mealvideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(vId, 0);
            }
        });
        mealsteps.setText( mealPlan.getStrInstructions());
        Glide.with(this).load( mealPlan.getStrMealThumb()).into(mealimage);
        gridLayoutManager=new GridLayoutManager(this,2);
        mealCardAdapter=new Meal_cardAdapter(this,ingredient,this);
        String[] ingredients = {
                mealPlan.getStrIngredient1(),
                mealPlan.getStrIngredient2(),
                mealPlan.getStrIngredient3(),
                mealPlan.getStrIngredient4(),
                mealPlan.getStrIngredient5(),
                mealPlan.getStrIngredient6(),
                mealPlan.getStrIngredient7(),
                mealPlan.getStrIngredient8(),
                mealPlan.getStrIngredient9(),
                mealPlan.getStrIngredient10(),
                mealPlan.getStrIngredient11(),
                mealPlan.getStrIngredient12()
        };
        for (String ingredientName : ingredients) {
            if (!ingredientName.isEmpty()) {
                ingredient.add(new Ingrdient(ingredientName));
            }
        }
        addtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onFavClick(model);
                Toast.makeText(MealDetail.this, "Meal Added to your Favourites", Toast.LENGTH_SHORT).show();
            }
        });



        ingrdentrv.setAdapter(mealCardAdapter);
        ingrdentrv.setLayoutManager(gridLayoutManager);
        //   mealCardAdapter.setMealList(ingrdient);
        mealCardAdapter.notifyDataSetChanged();

        mealPlan = new MealPlan( "sunday",  mealPlan.getIdMeal(), mealPlan.getStrMeal(),mealPlan.getStrMealThumb());
        addtoplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: meal detbebefore");
                showDayChooserDialog(model);
                Toast.makeText(MealDetail.this, "Meal Added to your Plans", Toast.LENGTH_SHORT).show();
                // onPlanClick(mealPlan);
            }
        });
    }*/

    @Override
    public void showerror(String error) {

    }



    @Override
    public void onDelClick(Meal meal) {
delelteFav(meal);
    }

    @Override
    public void onPlanClick(MealPlan mealPlan) {
        Log.i(TAG, "onPlanClick: mealdetail before");
        addToPlanmeal(mealPlan);

    }

    @Override
    public void onPlandelClick(MealPlan mealPlan) {

    }
}