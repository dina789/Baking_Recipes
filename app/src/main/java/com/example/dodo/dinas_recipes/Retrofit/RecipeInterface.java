package com.example.dodo.dinas_recipes.Retrofit;

import com.example.dodo.dinas_recipes.Models.Recipes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeInterface {

    @GET("baking.json")
    Call<ArrayList<Recipes>> getRecipe();
}
