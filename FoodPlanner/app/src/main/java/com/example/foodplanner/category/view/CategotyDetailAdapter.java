package com.example.foodplanner.category.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_detail.view.MealDetail;

import java.util.List;

public class CategotyDetailAdapter extends RecyclerView.Adapter<CategotyDetailAdapter.ViewHolder> {

    Context context;

    private List<Meal> list;
    private static final String TAG = "CategotyDetailAdapter";

    public CategotyDetailAdapter(Context context, List<Meal> list) {
        this.context = context;
        this.list = list;
    }
    public void setCategoryMealList(List<Meal> meals) {
        this.list= meals;
    }
    @NonNull
    @Override
    public CategotyDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_items,parent,false);
        CategotyDetailAdapter.ViewHolder viewHolder = new CategotyDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategotyDetailAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = list.get(position);
        holder.categoryitemname.setText(meal.getStrMeal());
        String url = meal.getStrMealThumb();
        Glide.with(context).load(url).into(holder.categoryitemimage);
        holder.categoryitemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealDetail.class);
                String m=list.get(position).getStrMeal();
                intent.putExtra("MEAL_NAME",m);
                context.startActivity(intent);
                Log.i(TAG, "onClick: "+m);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setCategoriesMealModelList(List<Meal> categoriesMealModelList) {
        this.list = categoriesMealModelList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView categoryitemname;
        ImageView categoryitemimage;
        CardView  categoryitemcard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryitemimage=itemView.findViewById(R.id.categoryitemimage);
            categoryitemname=itemView.findViewById(R.id.categoryitemtv);
            categoryitemcard=itemView.findViewById(R.id.categoryitemcard);
        }
    }
}
