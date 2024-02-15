package com.example.foodplanner.home.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.country.view.CountryScreen;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {


    private final Context context;

    private List<Meal> countrylist;

    public CountryAdapter(Context context, List<Meal> list) {
        this.context = context;
        this.countrylist= list;
    }

    private static final String TAG = "CountryAdapter";


    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_card,parent,false);
        CountryAdapter.ViewHolder viewHolder = new CountryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal country=countrylist.get(position);
      holder.countryname.setText(country.getStrArea());
        holder.countrycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CountryScreen.class);
                intent.putExtra("CON_MEAL", country.getStrArea());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return countrylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
   TextView countryname;
   CardView countrycard;
   View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
           countryname=itemView.findViewById(R.id.countrycardtext);
           countrycard=itemView.findViewById(R.id.countrycard);

        }
    }

    public void setContryList(List<Meal>meals) {
        this.countrylist = meals;
    }

}
