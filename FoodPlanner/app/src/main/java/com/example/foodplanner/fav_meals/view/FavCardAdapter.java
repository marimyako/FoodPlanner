package com.example.foodplanner.fav_meals.view;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DB.LocalDataSource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_detail.view.MealDetail;

import java.util.ArrayList;
import java.util.List;

public class FavCardAdapter extends RecyclerView.Adapter<FavCardAdapter.ViewHolder>  {

List<Meal> meals;

Context context;

OnClick onClick;

LocalDataSource localDataSource;

    public FavCardAdapter(List<Meal> meals, Context context, OnClick onClick) {
        this.meals = meals;
        this.context = context;
        this.onClick = onClick;
        meals=new ArrayList<Meal>();
    }

    @NonNull
    @Override
    public FavCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_favourites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavCardAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
      Meal meal=meals.get(position);
      holder.favnametv.setText(meal.getStrMeal());
      holder.favcountrytv.setText(meal.getStrCategory());
      String url=meal.getStrMealThumb();
      Glide.with(holder.favimage).load(url).into(holder.favimage);
      holder.delbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onClick.onDelClick(meal);
          }
      });
     holder.favcard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context, MealDetail.class);
             String m=meals.get(position).getStrMeal();
             intent.putExtra("MEAL_NAME",m);
             context.startActivity(intent);

         }
     });

    }
    public void setMealList(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView favcard;
        ImageView favimage;
        TextView favnametv;
        TextView favcountrytv;

        ImageView delbtn;
        ImageView backbtn;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            favcard=itemView.findViewById(R.id.favacard);
            favnametv=itemView.findViewById(R.id.favmealname);
            favimage=itemView.findViewById(R.id.favmealimage);
            favcountrytv=itemView.findViewById(R.id.favmealcountry);
            delbtn=itemView.findViewById(R.id.delbtn);

        }
    }
}
