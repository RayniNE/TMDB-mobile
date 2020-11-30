package com.example.primevideoclone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.primevideoclone.Model.BannerMovies;
import com.example.primevideoclone.MovieDetails;
import com.example.primevideoclone.R;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<BannerMovies> {

    private ArrayList<BannerMovies> bannerMoviesLists = new ArrayList<>();
    Context context;
    int resource;

    public MovieAdapter(@NonNull Context context, int resource, ArrayList<BannerMovies> bannerMoviesLists) {
        super(context, resource);
        this.bannerMoviesLists = bannerMoviesLists;
        this.context = context;
        this.resource = resource;


    }

    @Override
    public int getCount() {
        return bannerMoviesLists.size();
    }

    @Nullable
    @Override
    public BannerMovies getItem(int position) {
        return bannerMoviesLists.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String titulo = getItem(position).getMovieName();
        String estreno = bannerMoviesLists.get(position).getEstreno();

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        ImageView moviesImage = convertView.findViewById(R.id.moviesImage);
        TextView moviesTitle = convertView.findViewById(R.id.moviesTitle);
        TextView moviesEstreno = convertView.findViewById(R.id.moviesEstreno);
        ConstraintLayout listViewItem = convertView.findViewById(R.id.listViewItem);

        Glide.with(context).load(bannerMoviesLists.get(position).getImageUrl()).into(moviesImage);
        moviesTitle.setText(titulo);
        moviesEstreno.setText(estreno);

        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("movieId", bannerMoviesLists.get(position).getId());
                intent.putExtra("movieName", bannerMoviesLists.get(position).getMovieName());
                intent.putExtra("movieImageUrl", bannerMoviesLists.get(position).getImageUrl());
                intent.putExtra("movieFile", bannerMoviesLists.get(position).getFileUrl());
                intent.putExtra("movieEstreno", bannerMoviesLists.get(position).getEstreno());
                intent.putExtra("moviePopularity", bannerMoviesLists.get(position).getPopularidad());
                intent.putExtra("movieVotos", bannerMoviesLists.get(position).getVotes());
                intent.putExtra("movieDetail", bannerMoviesLists.get(position).getDetails());
                intent.putExtra("movieLanguage", bannerMoviesLists.get(position).getLanguage());
                context.startActivity(intent);

            }
        });

        return convertView;

    }
}
