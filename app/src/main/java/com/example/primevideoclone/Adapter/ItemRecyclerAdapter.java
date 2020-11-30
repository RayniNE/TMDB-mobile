package com.example.primevideoclone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.primevideoclone.Model.CategoryItem;
import com.example.primevideoclone.MovieDetails;
import com.example.primevideoclone.R;

import java.util.ArrayList;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    Context context;
    ArrayList<CategoryItem> categoryItemArrayList;

    public ItemRecyclerAdapter(Context context, ArrayList<CategoryItem> categoryItemArrayList) {
        this.context = context;
        this.categoryItemArrayList = categoryItemArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_items, parent, false));
    }

    @Override
    public int getItemCount() {
        return categoryItemArrayList.size();
    }


    public CategoryItem getItem(int position) {
        return categoryItemArrayList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        //holder.itemImage.setImageResource(categoryItemArrayList.get(position).getImagenUrl());
        //Utilizamos la libreria Glide para conseguir la imagen a partir de la direccion y se inserate en el ImageView
        Glide.with(context).load(getItem(position).getImageUrl()).into(holder.itemImage);
        int hola = getItemCount();
        Log.i("hola", "" + hola);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("movieId", categoryItemArrayList.get(position).getId());
                Log.i("movieId", getItem(position).getMovieName());
                intent.putExtra("movieName", categoryItemArrayList.get(position).getMovieName());
                intent.putExtra("movieImageUrl", categoryItemArrayList.get(position).getImageUrl());
                intent.putExtra("movieFile", categoryItemArrayList.get(position).getFileUrl());
                intent.putExtra("movieEstreno", categoryItemArrayList.get(position).getEstreno());
                intent.putExtra("moviePopularity", categoryItemArrayList.get(position).getPopularidad());
                intent.putExtra("movieVotos", categoryItemArrayList.get(position).getVotes());
                intent.putExtra("movieDetail", categoryItemArrayList.get(position).getDetails());
                intent.putExtra("movieLanguage", categoryItemArrayList.get(position).getLanguage());
                context.startActivity(intent);

            }
        });

    }



    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImage);

        }
    }


}
