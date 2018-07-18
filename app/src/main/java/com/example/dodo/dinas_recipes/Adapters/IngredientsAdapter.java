package com.example.dodo.dinas_recipes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dodo.dinas_recipes.Models.Ingredients;
import com.example.dodo.dinas_recipes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {


    private ArrayList<Ingredients> ingredientsModelList;

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_detail_fragment, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.ingredientTitle.setText(ingredientsModelList.get(position).getIngredient());
        holder.ingredientQuantity.setText(" " + String.valueOf(ingredientsModelList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (ingredientsModelList == null){
            return -1
                    ;}

        return ingredientsModelList.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Ingredients_title)
        TextView ingredientTitle;
        @BindView(R.id.ingredient_quantity) TextView ingredientQuantity;


        IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setIngredientsModelList(ArrayList<Ingredients> ingredientsModelList) {
        this.ingredientsModelList = ingredientsModelList;
    }
}
