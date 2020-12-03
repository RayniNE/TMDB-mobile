package com.example.primevideoclone.Retrofit;

import com.example.primevideoclone.Model.AllFamilyMovies;
import com.example.primevideoclone.Model.AllTvShows;
import com.example.primevideoclone.Model.BannerMoviesList;
import com.example.primevideoclone.Model.MovieTrailer;
import com.example.primevideoclone.Model.TvTrailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular?api_key=97a742234f5d50b393ebe860d7e2ba63&language=en-US&page=1")
    Call<BannerMoviesList> getAllBanners();

    @GET("movie/{movieId}/videos?api_key=97a742234f5d50b393ebe860d7e2ba63&language=en-US")
    Call<MovieTrailer> getMovieUrl(
            @Path("movieId") int movieId
    );

    @GET("tv/popular?api_key=97a742234f5d50b393ebe860d7e2ba63&language=en-US&page=1")
    Call<AllTvShows> getTvShows();

    @GET("tv/{tvShowId}/videos?api_key=97a742234f5d50b393ebe860d7e2ba63&language=en-US")
    Call<TvTrailer> getTvShowUrl(
            @Path("tvShowId") int tvShowId
    );

    @GET("discover/movie?api_key=97a742234f5d50b393ebe860d7e2ba63&certification_country=US&certification.lte=G&sort_by=popularity.desc")
    Call<AllFamilyMovies> getKidsMovies();

    @GET("movie/{movieId}/videos?api_key=97a742234f5d50b393ebe860d7e2ba63&language=en-US")
    Call<MovieTrailer> getFamMovieUrl(
            @Path("movieId") int movieId
    );

    @GET("discover/movie?api_key=97a742234f5d50b393ebe860d7e2ba63")
    Call<BannerMoviesList> getMoviesByCategory(
            @Query("with_genres") int with_genres
    );

    @GET("search/movie?api_key=97a742234f5d50b393ebe860d7e2ba63&language=en-US&page=1&include_adult=false")
    Call<BannerMoviesList> getMovieSearch(
            @Query("query") String query
    );

}
