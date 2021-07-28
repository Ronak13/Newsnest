package com.example.newsnest.utils;

import com.example.newsnest.model.FetchNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<FetchNews> getNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String key
    );

    @GET("top-headlines")
    Call<FetchNews> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String key
    );

}
