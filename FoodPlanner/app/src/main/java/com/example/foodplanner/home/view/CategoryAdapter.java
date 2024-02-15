package com.example.foodplanner.home.view;

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
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;
import com.example.foodplanner.category.view.CategoryScreen;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final Context context;
    private List<Category> categorylist;
    private static final String TAG = "CategoryAdapter";

    public CategoryAdapter(Context context, List<Category> list) {
        this.context = context;
        this.categorylist = list;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
     public TextView categoryname;
      public ImageView categoryimage;

      public View view;
      public CardView categorycard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            categoryname=itemView.findViewById(R.id.categorycardtext);
            categoryimage=itemView.findViewById(R.id.categorycardimage);
            categorycard=itemView.findViewById(R.id.categorycard);
        }
    }
    public void setCategoryList(List<Category> categories) {
        this.categorylist = categories;
    }
    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_card,parent,false);
        ViewHolder viewHolder = new CategoryAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category=categorylist.get(position);
        holder.categoryname.setText(category.getStrCategory());
        String url = category.getStrCategoryThumb();
        Glide.with(context).load(url).into(holder.categoryimage);
        holder.categorycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CategoryScreen.class);
                intent.putExtra("CAT_MEAL", category.getStrCategory());
                context.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return categorylist.size();
    }
}
