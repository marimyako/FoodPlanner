package com.example.foodplanner.country.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_detail.view.MealDetail;

import java.util.List;

public class CountryDetailAdapter extends RecyclerView.Adapter<CountryDetailAdapter.ViewHolder>  {
    Context context;

    public CountryDetailAdapter(Context context, List<Meal> list) {
        this.context = context;
        this.list = list;
    }

    private List<Meal> list;


    public void setCountryMealList(List<Meal> meals) {
        this.list= meals;
    }
    @NonNull
    @Override
    public CountryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_items,parent,false);
        CountryDetailAdapter.ViewHolder viewHolder = new CountryDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryDetailAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = list.get(position);
        holder.countryitemname.setText(meal.getStrMeal());
        String url = meal.getStrMealThumb();
        Glide.with(context).load(url).into(holder.countryitemimage);
        holder.countryitemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealDetail.class);
                String m=list.get(position).getStrMeal();
                intent.putExtra("MEAL_NAME",m);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setCountryMealModelList(List<Meal> countriesMealModelList) {
        this.list = countriesMealModelList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView countryitemname;
        ImageView countryitemimage;
        CardView countryitemcard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryitemimage=itemView.findViewById(R.id.countryitemimage);
            countryitemname=itemView.findViewById(R.id.countryitemtv);
            countryitemcard=itemView.findViewById(R.id.countryitemcard);
        }
    }
}
