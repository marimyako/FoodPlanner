package com.example.foodplanner.search.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.R;
import com.example.foodplanner.country.view.CountryScreen;

import java.util.List;

public class SearchByCountryAdapter extends RecyclerView.Adapter<SearchByCountryAdapter.ViewHolder>{
private final Context context;

private List<Country> countrylist;

SearchOnClick searchClickInterface;

public SearchByCountryAdapter(Context context, List<Country> list, SearchOnClick searchClickInterface) {
        this.context = context;
        this.countrylist= list;
        this.searchClickInterface=searchClickInterface;
        }

private static final String TAG = "CountryAdapter";


@NonNull
@Override
public SearchByCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_card,parent,false);
    SearchByCountryAdapter.ViewHolder viewHolder = new SearchByCountryAdapter.ViewHolder(view);
        return viewHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country=countrylist.get(position);
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
        return countrylist != null ? countrylist.size() : 0;
    }

    public void setContryList(List<Country> countries) {
        this.countrylist = countries;
        notifyDataSetChanged();
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


}
