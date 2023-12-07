package com.example.uasapk.api;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    private String title;


    public String getTitle() {
        return title;
    }

}
