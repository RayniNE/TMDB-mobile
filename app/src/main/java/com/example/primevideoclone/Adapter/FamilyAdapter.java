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
import com.example.primevideoclone.Model.FamilyMovie;
import com.example.primevideoclone.MovieDetails;
import com.example.primevideoclone.R;

import java.util.ArrayList;

public class FamilyAdapter extends ArrayAdapter<FamilyMovie> {

    private ArrayList<FamilyMovie> familyMovies = new ArrayList<>();
    Context context;
    int resource;

    public FamilyAdapter(@NonNull Context context, int resource, ArrayList<FamilyMovie> familyMovies) {
        super(context, resource);
        this.familyMovies = familyMovies;
        this.context = context;
        this.resource = resource;


    }

    @Override
    public int getCount() {
        return familyMovies.size();
    }

    @Nullable
    @Override
    public FamilyMovie getItem(int position) {
        return familyMovies.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String titulo = getItem(position).getMovieName();
        String estreno = familyMovies.get(position).getEstreno();

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        ImageView moviesImage = convertView.findViewById(R.id.moviesImage);
        TextView moviesTitle = convertView.findViewById(R.id.moviesTitle);
        TextView moviesEstreno = convertView.findViewById(R.id.moviesEstreno);
        ConstraintLayout listViewItem = convertView.findViewById(R.id.listViewItem);

        Glide.with(context).load(familyMovies.get(position).getImageUrl()).into(moviesImage);
        moviesTitle.setText(titulo);
        moviesEstreno.setText(estreno);

        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("movieId", familyMovies.get(position).getId());
                intent.putExtra("movieName", familyMovies.get(position).getMovieName());
                intent.putExtra("movieImageUrl", familyMovies.get(position).getImageUrl());
                intent.putExtra("movieFile", familyMovies.get(position).getFileUrl());
                intent.putExtra("movieEstreno", familyMovies.get(position).getEstreno());
                intent.putExtra("moviePopularity", familyMovies.get(position).getPopularidad());
                intent.putExtra("movieVotos", familyMovies.get(position).getVotes());
                intent.putExtra("movieDetail", familyMovies.get(position).getDetails());
                intent.putExtra("movieLanguage", familyMovies.get(position).getLanguage());
                context.startActivity(intent);

            }
        });

        return convertView;

    }
}

