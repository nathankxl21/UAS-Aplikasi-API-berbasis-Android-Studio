package com.example.uasapk;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uasapk.api.Movie;
import com.example.uasapk.api.MovieAdapter;
import com.example.uasapk.api.MovieApiService;
import com.example.uasapk.api.MovieResponse;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "cb977fb0b9ceab3f8726c0b739165fba";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private MovieAdapter movieAdapter;
    private List<Movie> allMovies;
    private MovieApiService movieApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create MovieApiService instance
        movieApiService = retrofit.create(MovieApiService.class);

        // Initialize the adapter and set it to the ListView
        movieAdapter = new MovieAdapter(this, new ArrayList<>());
        ListView listView = findViewById(R.id.movieListView);
        listView.setAdapter(movieAdapter);

        // Make API call to get popular movies
        Call<MovieResponse> call = movieApiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    // Handle the list of movies
                    List<Movie> movies = response.body().getMovies();
                    allMovies = movies;
                    // Update the adapter with all movies initially
                    movieAdapter.addAll(movies);
                } else {
                    showToast("Failed to get movies");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                showToast("Network error");
            }
        });
    }

    // Method to show Toast messages
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
