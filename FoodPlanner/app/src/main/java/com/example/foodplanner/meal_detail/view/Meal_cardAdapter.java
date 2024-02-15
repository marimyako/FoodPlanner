package com.example.foodplanner.meal_detail.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingrdient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.List;

public class Meal_cardAdapter extends RecyclerView.Adapter<Meal_cardAdapter.ViewHolder> {

        Context context;

        List<Ingrdient> ingrdientofmeals;

    public void setMealList(List<Ingrdient> meals) {
        this.ingrdientofmeals= meals;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingrdentname;

        ImageView ingrdentimage;
        View view;
        CardView ingrdentcard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            ingrdentname=itemView.findViewById(R.id.ingrdenttv);
            ingrdentimage=itemView.findViewById(R.id.ingrdentimage);
            ingrdentcard=itemView.findViewById(R.id.ingrdentcard);


        }
    }


    public Meal_cardAdapter(Context context, List<Ingrdient> meal) {
        this.context = context;
        this.ingrdientofmeals = meal;
    }


    @NonNull
    @Override
    public Meal_cardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.ingredient_card,parent,false);
        Meal_cardAdapter.ViewHolder viewHolder = new Meal_cardAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Meal_cardAdapter.ViewHolder holder, int position) {
        holder.ingrdentname.setText(ingrdientofmeals.get(position).getName());
        String url = ingrdientofmeals.get(position).getImage();
        Glide.with(context).load(url).into(holder.ingrdentimage);
    }

    @Override
    public int getItemCount() {
        return ingrdientofmeals.size();
    }


}
