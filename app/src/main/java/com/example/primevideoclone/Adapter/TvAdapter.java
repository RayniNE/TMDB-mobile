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
import com.example.primevideoclone.Model.TvShow;
import com.example.primevideoclone.R;
import com.example.primevideoclone.TvDetails;

import java.util.ArrayList;

public class TvAdapter extends ArrayAdapter<TvShow> {

    private ArrayList<TvShow> tvShowList = new ArrayList<>();
    Context context;
    int resource;

    public TvAdapter(@NonNull Context context, int resource, ArrayList<TvShow> tvShowList) {
        super(context, resource);
        this.tvShowList = tvShowList;
        this.context = context;
        this.resource = resource;


    }

    @Override
    public int getCount() {
        return tvShowList.size();
    }

    @Nullable
    @Override
    public TvShow getItem(int position) {
        return tvShowList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String titulo = getItem(position).getMovieName();
        String estreno = tvShowList.get(position).getEstreno();

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        ImageView moviesImage = convertView.findViewById(R.id.moviesImage);
        TextView moviesTitle = convertView.findViewById(R.id.moviesTitle);
        TextView moviesEstreno = convertView.findViewById(R.id.moviesEstreno);
        ConstraintLayout listViewItem = convertView.findViewById(R.id.listViewItem);

        Glide.with(context).load(tvShowList.get(position).getImageUrl()).into(moviesImage);
        moviesTitle.setText(titulo);
        moviesEstreno.setText(estreno);

        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TvDetails.class);
                intent.putExtra("movieId", tvShowList.get(position).getId());
                intent.putExtra("movieName", tvShowList.get(position).getMovieName());
                intent.putExtra("movieImageUrl", tvShowList.get(position).getImageUrl());
                intent.putExtra("movieFile", tvShowList.get(position).getFileUrl());
                intent.putExtra("movieEstreno", tvShowList.get(position).getEstreno());
                intent.putExtra("moviePopularity", tvShowList.get(position).getPopularidad());
                intent.putExtra("movieVotos", tvShowList.get(position).getVotes());
                intent.putExtra("movieDetail", tvShowList.get(position).getDetails());
                intent.putExtra("movieLanguage", tvShowList.get(position).getLanguage());
                context.startActivity(intent);

            }
        });

        return convertView;

    }
}
