package com.example.primevideoclone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.primevideoclone.Adapter.FamilyAdapter;
import com.example.primevideoclone.Model.AllFamilyMovies;
import com.example.primevideoclone.Model.FamilyMovie;
import com.example.primevideoclone.R;
import com.example.primevideoclone.Retrofit.ApiInterface;
import com.example.primevideoclone.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Movies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Family extends Fragment {

    private View view;
    private ListView tvListView;
    private ArrayList<FamilyMovie> familyMovies;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Family() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Movies.
     */
    // TODO: Rename and change types and number of parameters
    public static TvShows newInstance(String param1, String param2) {
        TvShows fragment = new TvShows();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_family, container, false);
        tvListView = view.findViewById(R.id.familyListView);
        familyMovies = new ArrayList<>();

        getFamMovies();


        return view;
    }

    private void setFamilyMoviesPagerAdapter(ArrayList<FamilyMovie> familyMovies){

        FamilyAdapter adapter = new FamilyAdapter(getContext(), R.layout.movies_listview_item, familyMovies);
        adapter.notifyDataSetChanged();
        tvListView.setAdapter(adapter);


    }


    private void getFamMovies(){


        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<AllFamilyMovies> call = myInterface.getKidsMovies();

        call.enqueue(new Callback<AllFamilyMovies>() {
            @Override
            public void onResponse(Call<AllFamilyMovies> call, Response<AllFamilyMovies> response) {
                AllFamilyMovies results = response.body();
                List<AllFamilyMovies.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < listOfBanners.size(); i++){
                    int id = listOfBanners.get(i).getId();
                    String tvName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    familyMovies.add(new FamilyMovie(id, tvName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                }
                setFamilyMoviesPagerAdapter(familyMovies);


            }

            @Override
            public void onFailure(Call<AllFamilyMovies> call, Throwable t) {

            }
        });



    }
}