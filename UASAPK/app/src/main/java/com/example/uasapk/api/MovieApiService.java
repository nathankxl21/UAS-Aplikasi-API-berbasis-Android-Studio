package com.example.uasapk.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieApiService {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query);

    @GET("discover/movie")
    Call<MovieResponse> getMoviesByReleaseDate(
            @Query("api_key") String apiKey,
            @Query("primary_release_date.gte") String releaseDate);
}



