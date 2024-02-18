package com.example.foodplanner.MealPlan.view;

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
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealPlan;
import com.example.foodplanner.OnClick;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_detail.view.MealDetail;

import java.util.List;

public class MealPlanAdapter extends RecyclerView.Adapter<MealPlanAdapter.ViewHolder> {

    Context context;

    private List<Meal> list;
    OnClick onClick;
    private static final String TAG = "CategotyDetailAdapter";

    private List<MealPlan> mealPlans;

    public MealPlanAdapter(Context context, List<MealPlan> mealPlans,OnClick onClick) {
        this.context = context;
        this.mealPlans = mealPlans;
        this.onClick=onClick;
    }
    public void setMealplanList(List<MealPlan> mealPlans) {
        this.mealPlans= mealPlans;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MealPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.planmeal_card,parent,false);
        MealPlanAdapter.ViewHolder viewHolder = new MealPlanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MealPlan meal = mealPlans.get(position);
        holder.planitemname.setText(meal.getIdMeal());
        String url = meal.getStrMeal();
        Glide.with(context).load(url).into(holder.planitemimage);
        holder.planitemcard.setOnClickListener(new View.OnClickListener() {
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
        return mealPlans.size();
    }
    public void setCategoriesMealModelList(List<Meal> categoriesMealModelList) {
        this.list = categoriesMealModelList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView planitemname;
        ImageView planitemimage;
        CardView planitemcard;

        ImageView addbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planitemimage=itemView.findViewById(R.id.planitemimage);
            planitemname=itemView.findViewById(R.id.planitemtv);
            planitemcard=itemView.findViewById(R.id.planitemcard);
            addbtn=itemView.findViewById(R.id.addFavorite);
        }
    }
}
