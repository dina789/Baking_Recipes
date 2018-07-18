package com.example.dodo.dinas_recipes.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dodo.dinas_recipes.Adapters.IngredientsAdapter;
import com.example.dodo.dinas_recipes.Adapters.StepsOfRecipesAdapter;
import com.example.dodo.dinas_recipes.Models.Ingredients;
import com.example.dodo.dinas_recipes.Models.Steps;
import com.example.dodo.dinas_recipes.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The fragment to hold the recycler view of the ingredients and steps of the recipe.
 */
public class Recipe_Detail_Fragment extends Fragment implements StepsOfRecipesAdapter.StepItemClickListener {
    @BindView(R.id.rv_ingredients)
    RecyclerView rv_ingredients;

    @BindView(R.id.rv_steps)
    RecyclerView rv_steps;
    StepsOfRecipesAdapter Sadapter;
    IngredientsAdapter Iadapter;
    @BindView(R.id.Ingredients_title)
    TextView Ingredients_title;

    @BindView(R.id.Steps_title)
    TextView Steps_title;

    private ArrayList<Ingredients> ingredientsModelList;

    private List<Steps> stepsModelList;
//empty constructor

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//inflate layout


        View rootView = inflater.inflate(R.layout.ingredients_items, container, false);
        ButterKnife.bind(this, rootView);

        rv_ingredients.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        Iadapter = new IngredientsAdapter();
        /* Setting the adapter attaches it to the RecyclerView in our layout. */

        rv_ingredients.setAdapter(Iadapter);
        rv_ingredients.setNestedScrollingEnabled(false);
        Iadapter.setIngredientsModelList(ingredientsModelList);


        rv_steps.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));

        rv_steps.setAdapter(Sadapter);

        rv_steps.setNestedScrollingEnabled(false);

        return rootView;

    }


    @Override
    public void onClickStep(int position) {
        OnStepClickListener StepsOnClickListener =( OnStepClickListener) getActivity();
        Bundle bundle = new Bundle();

        bundle.putString("Step URL", stepsModelList.get(position).getVideoURL());
        bundle.putString("Step Description",stepsModelList.get(position).getDescription());
        bundle.putString("Step thumbnail",stepsModelList.get(position).getThumbnailURL());
     StepsOnClickListener.OnStepitemClicked(bundle);







    }

    public void setIngredientsModelList(ArrayList<Ingredients> ingredientsModelList) {
        this.ingredientsModelList = ingredientsModelList;
    }

    public void setStepsModelList(List<Steps> stepsModelList) {
        this.stepsModelList = stepsModelList;
    }

    public interface  OnStepClickListener{
        void OnStepitemClicked(Bundle bundle);
    }

}