package com.example.primevideoclone.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.primevideoclone.Adapter.BannerMoviesPagerAdapter;
import com.example.primevideoclone.Adapter.MainRecyclerAdapter;
import com.example.primevideoclone.Model.AllCategory;
import com.example.primevideoclone.Model.BannerMovies;
import com.example.primevideoclone.Model.BannerMoviesList;
import com.example.primevideoclone.Model.CategoryItem;
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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    View view;
    ArrayList<BannerMovies> homeBannerList;
    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView recyclerView;
    ArrayList<AllCategory> allCategoryArrayList;
    NestedScrollView nestedScrollView;
    ViewPager bannerViewPager;
    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    String videoUrl;
    ArrayList<CategoryItem> homeCatListItem;
    ArrayList<CategoryItem> homeCatListItem2;;
    ArrayList<CategoryItem> homeCatListItem3;;
    ArrayList<CategoryItem> homeCatListItem4;;
    ArrayList<CategoryItem> homeCatListItem5;;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        nestedScrollView = view.findViewById(R.id.nestedScroll);
        homeBannerList = new ArrayList<>();
        homeCatListItem = new ArrayList<>();
        homeCatListItem2 = new ArrayList<>();
        homeCatListItem3 = new ArrayList<>();
        homeCatListItem4 = new ArrayList<>();
        homeCatListItem5 = new ArrayList<>();

        getComedyMovies(35);
        getFamiliarMovies(10751);
        getHistoryMovies(36);
        getHorrorMovies(27);

        allCategoryArrayList = new ArrayList<>();

        allCategoryArrayList.add(new AllCategory(1, "Comedia", homeCatListItem2));
        allCategoryArrayList.add(new AllCategory(2, "Familiar", homeCatListItem3));
        allCategoryArrayList.add(new AllCategory(3, "Historia", homeCatListItem4));
        allCategoryArrayList.add(new AllCategory(4, "Horror", homeCatListItem5));

        getAllBanners();

        return view;
    }

    private void setBannerMoviesPagerAdapter(ArrayList<BannerMovies> bannerMoviesList){
        bannerViewPager = view.findViewById(R.id.bannerViewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(getContext(), bannerMoviesList);
        bannerViewPager.setAdapter(bannerMoviesPagerAdapter);

    }

    public void setMainRecyclerAdapter(ArrayList<AllCategory> allCategoryArrayList){

        recyclerView = view.findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryArrayList);
        recyclerView.setAdapter(mainRecyclerAdapter);

    }



    private void getAllBanners(){


        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<BannerMoviesList> call = myInterface.getAllBanners();

        call.enqueue(new Callback<BannerMoviesList>() {
            @Override
            public void onResponse(Call<BannerMoviesList> call, Response<BannerMoviesList> response) {
                BannerMoviesList results = response.body();
                List<BannerMoviesList.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < 10; i++){
                    int id = listOfBanners.get(i).getId();
                    String movieName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    //String finalUrl = ExtractYoutubeVideo(fileUrl);
                    homeBannerList.add(new BannerMovies(id, movieName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                }
                setBannerMoviesPagerAdapter(homeBannerList);
                getActionMovies(28);


            }

            @Override
            public void onFailure(Call<BannerMoviesList> call, Throwable t) {

            }
        });

    }

    private void getActionMovies(int categoryId){

        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<BannerMoviesList> call = myInterface.getMoviesByCategory(categoryId);

        call.enqueue(new Callback<BannerMoviesList>() {
            @Override
            public void onResponse(Call<BannerMoviesList> call, Response<BannerMoviesList> response) {
                BannerMoviesList results = response.body();
                List<BannerMoviesList.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < 10; i++){
                    int id = listOfBanners.get(i).getId();
                    String movieName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    homeCatListItem.add(new CategoryItem(id, movieName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                    Log.i("movieName", homeCatListItem.get(i).getMovieName());
                }
                setMainRecyclerAdapter(allCategoryArrayList);
            }


            @Override
            public void onFailure(Call<BannerMoviesList> call, Throwable t) {

            }
        });

    }

    private void getComedyMovies(int categoryId){


        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<BannerMoviesList> call = myInterface.getMoviesByCategory(categoryId);

        call.enqueue(new Callback<BannerMoviesList>() {
            @Override
            public void onResponse(Call<BannerMoviesList> call, Response<BannerMoviesList> response) {
                BannerMoviesList results = response.body();
                List<BannerMoviesList.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < 10; i++){
                    int id = listOfBanners.get(i).getId();
                    String movieName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    //String finalUrl = ExtractYoutubeVideo(fileUrl);
                    homeCatListItem2.add(new CategoryItem(id, movieName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                }

            }

            @Override
            public void onFailure(Call<BannerMoviesList> call, Throwable t) {

            }
        });



    }

    private void getFamiliarMovies(int categoryId){


        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<BannerMoviesList> call = myInterface.getMoviesByCategory(categoryId);

        call.enqueue(new Callback<BannerMoviesList>() {
            @Override
            public void onResponse(Call<BannerMoviesList> call, Response<BannerMoviesList> response) {
                BannerMoviesList results = response.body();
                List<BannerMoviesList.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < 10; i++){
                    int id = listOfBanners.get(i).getId();
                    String movieName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    //String finalUrl = ExtractYoutubeVideo(fileUrl);
                    homeCatListItem3.add(new CategoryItem(id, movieName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                }

            }

            @Override
            public void onFailure(Call<BannerMoviesList> call, Throwable t) {

            }
        });



    }

    private void getHistoryMovies(int categoryId){


        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<BannerMoviesList> call = myInterface.getMoviesByCategory(categoryId);

        call.enqueue(new Callback<BannerMoviesList>() {
            @Override
            public void onResponse(Call<BannerMoviesList> call, Response<BannerMoviesList> response) {
                BannerMoviesList results = response.body();
                List<BannerMoviesList.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < 10; i++){
                    int id = listOfBanners.get(i).getId();
                    String movieName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    //String finalUrl = ExtractYoutubeVideo(fileUrl);
                    homeCatListItem4.add(new CategoryItem(id, movieName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                }

            }

            @Override
            public void onFailure(Call<BannerMoviesList> call, Throwable t) {

            }
        });



    }

    private void getHorrorMovies(int categoryId){


        ApiInterface myInterface = RetrofitClient.getRetrofitClient();
        Call<BannerMoviesList> call = myInterface.getMoviesByCategory(categoryId);

        call.enqueue(new Callback<BannerMoviesList>() {
            @Override
            public void onResponse(Call<BannerMoviesList> call, Response<BannerMoviesList> response) {
                BannerMoviesList results = response.body();
                List<BannerMoviesList.ResultsBean> listOfBanners = results.getResults();
                for(int i = 0; i < 10; i++){
                    int id = listOfBanners.get(i).getId();
                    String movieName = listOfBanners.get(i).getTitle();
                    String imageUrl = "https://image.tmdb.org/t/p/w500/"+listOfBanners.get(i).getPoster_path();
                    String fileUrl = "https://www.youtube.com/watch?v=";
                    String estreno = listOfBanners.get(i).getRelease_date();
                    double popularity = listOfBanners.get(i).getPopularity();
                    double votos = listOfBanners.get(i).getVote_average();
                    String details = listOfBanners.get(i).getOverview();
                    String language = listOfBanners.get(i).getOriginal_language();
                    //String finalUrl = ExtractYoutubeVideo(fileUrl);
                    homeCatListItem5.add(new CategoryItem(id, movieName, imageUrl, fileUrl, estreno, popularity , votos, details, language));
                }

            }

            @Override
            public void onFailure(Call<BannerMoviesList> call, Throwable t) {

            }
        });



    }



}