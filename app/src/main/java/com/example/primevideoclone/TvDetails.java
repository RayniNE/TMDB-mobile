package com.example.primevideoclone;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.primevideoclone.Model.TvTrailer;
import com.example.primevideoclone.Retrofit.ApiInterface;
import com.example.primevideoclone.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvDetails extends AppCompatActivity {

    TextView movieName;
    TextView textViewEstreno;
    TextView textViewPopularity;
    TextView textViewVote;
    TextView textViewDetails;
    TextView textViewLanguage;
    ImageView movieImage;
    Button playButton;
    String mName, mImage, mUrl, trailerId, estreno, details, language;
    int mId;
    double popularidad, votos;
    MutableLiveData<String> youtubeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        youtubeId = new MutableLiveData<>();

        playButton = findViewById(R.id.playButton);
        movieImage = findViewById(R.id.movieImage);
        movieName = findViewById(R.id.movieName);
        textViewEstreno = findViewById(R.id.estreno);
        textViewPopularity = findViewById(R.id.popularidad);
        textViewVote = findViewById(R.id.vote);
        textViewDetails = findViewById(R.id.info);
        textViewLanguage = findViewById(R.id.language);

        mId = getIntent().getIntExtra("movieId", 0);
        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mUrl = getIntent().getStringExtra("movieFile");
        estreno = getIntent().getStringExtra("movieEstreno");
        details = getIntent().getStringExtra("movieDetail");
        language = getIntent().getStringExtra("movieLanguage");
        popularidad = getIntent().getDoubleExtra("moviePopularity", 0);
        votos = getIntent().getDoubleExtra("movieVotos", 0);

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);
        textViewEstreno.setText(estreno);
        textViewPopularity.setText(Double.toString(popularidad));
        textViewVote.setText(votos + " / 10");
        textViewDetails.setText(details);
        textViewLanguage.setText(language);

        youtubeId.observe(this, (nuevoId) -> {
            trailerId = nuevoId;
        });
        getTvTrailer();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerId));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl + trailerId));
                try{
                    startActivity(intent);
                } catch(ActivityNotFoundException ex) {
                    startActivity(webIntent);
                }
            }
        });


    }

    private void getTvTrailer(){
        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<TvTrailer> call = myInterface.getTvShowUrl(mId);
        call.enqueue(new Callback<TvTrailer>() {
            @Override
            public void onResponse(Call<TvTrailer> call, Response<TvTrailer> response) {

                if(response.isSuccessful()){
                    TvTrailer result = response.body();
                    List<TvTrailer.ResultsBean> movie = result.getResults();
                    youtubeId.setValue(movie.get(0).getKey());

                }

            }

            @Override
            public void onFailure(Call<TvTrailer> call, Throwable t) {

            }
        });

    }

}