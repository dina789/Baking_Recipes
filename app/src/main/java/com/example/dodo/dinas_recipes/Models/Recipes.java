package com.example.dodo.dinas_recipes.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipes implements Serializable {

    private int id;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private int servings;
    private String image;
    public ArrayList<Ingredients> ingredientsArrayList;
    public ArrayList<Steps> stepsArrayList;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
