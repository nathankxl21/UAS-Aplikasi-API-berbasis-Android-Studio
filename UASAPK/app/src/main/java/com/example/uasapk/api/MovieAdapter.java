package com.example.uasapk.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private LayoutInflater inflater;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Movie movie = getItem(position);
        TextView titleTextView = view.findViewById(android.R.id.text1);

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
        }

        return view;
    }
}
