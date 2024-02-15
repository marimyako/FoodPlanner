package com.example.foodplanner.home.view;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.category.view.CategoryScreen;
import com.example.foodplanner.meal_detail.view.MealDetail;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {
    private static final String TAG = "ViewAdapter";
    private final Context context;

    private List<Meal> list;


    public ViewAdapter(Context context, List<Meal> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public CardView cardFavorate ;
        public CardView cardItem;
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView =itemView.findViewById(R.id.imageItem);
            name = itemView.findViewById(R.id.itemText);
            cardItem = itemView.findViewById(R.id.pagerItem);
            cardFavorate = itemView.findViewById(R.id.itemCardFavorite);
        }
    }
    public void setViewPagerAdepterList(List<Meal> mealModels) {
        this.list = mealModels;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.inspirationcard,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meal meal = list.get(position);
        holder.name.setText(meal.getStrMeal());
        String url = meal.getStrMealThumb();
        Glide.with(context).load(url).into(holder.imageView);
        holder.cardItem.setOnClickListener(new View.OnClickListener() {
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
}
