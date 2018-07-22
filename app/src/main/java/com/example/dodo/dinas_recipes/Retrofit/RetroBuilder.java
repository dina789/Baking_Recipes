package com.example.dodo.dinas_recipes.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroBuilder {


    static RecipeInterface Recipe;


    public static RecipeInterface Retrieve() {

        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        Recipe = new Retrofit.Builder()
                .baseUrl("http://go.udacity.com/android-baking-app-json")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callFactory(httpClientBuilder.build())
                .build().create(RecipeInterface.class);


        return Recipe;
    }
}