package com.example.dodo.dinas_recipes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dodo.dinas_recipes.Models.Recipes;
import com.example.dodo.dinas_recipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecyclerViewHolder> {

    private Context mContext;
    private ArrayList<Recipes> recipes;
    final private ListItemClickListener OnItemClickListener;
    private ListItemClickListener recipeListOnClickListenerInteface;


    public RecipeListAdapter(ArrayList<Recipes> recipeArrayList, ListItemClickListener listener) {
        this.OnItemClickListener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.recipes_items, parent, false);
        return new RecyclerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


        Context context = holder.itemView.getContext();
        String recipeName = recipes.get(position).getName();
        String imagePath = recipes.get(position).getImage();
        int recipePic;

        switch (recipeName) {
            case "Nutella Pie":
                recipePic = R.drawable.nutella_cake;
                break;
            case "Brownies":
                recipePic = R.drawable.brownies;
                break;
            case "Yellow Cake":
                recipePic = R.drawable.cheese_cake;
                break;
            case "Cheesecake":
                recipePic = R.drawable.cheese_cake;
                break;
            default:
                recipePic = R.drawable.placeholder;
        }

        holder.recipe_title.setText(recipeName);
//if image = null             // Image was not found in cache; load it from the server

        if (imagePath.equals("")) {
            Picasso.with(context)
                    .load(recipePic)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.recipe_thumbnail);
        } else {
            Picasso.with(context)
                    .load(imagePath)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.recipe_thumbnail);
        }
    }

    @Override
    public int getItemCount() {

        if (recipes == null) {
            return -1;
        }
        return recipes.size();


    }

    public void setRecipeModelList(ArrayList<Recipes> recipesList, Context context) {

        mContext = context;


        recipes = recipesList;
        notifyDataSetChanged();
    }


    /**
     * The interface that receives onClick messages.
     */

    public interface ListItemClickListener {
        void onListItemClick(Recipes recipes);
    }


 /* It's also a convenient place to set an
                             OnClickListener, since it has access to the adapter and the views.*/

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_thumbnail)
        ImageView recipe_thumbnail;
        @BindView(R.id.recipe_title)
        TextView recipe_title;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        /**
         * This gets called by the child views during a click. We fetch the date that has been
         * selected, and then call the onClick handler registered with this adapter, passing that
         * date.
         *
         * @param view the View that was clicked
         */
        @Override
        public void onClick(View view) {

            Recipes currentrecipe = recipes.get(getAdapterPosition());


            OnItemClickListener.onListItemClick(currentrecipe);


        }

    }
}
//http://whats-online.info/science-and-tutorials/87/Android-tutorial-Horizontal-RecyclerView-with-images-and-text-example/
//https://dzone.com/articles/dynamically-loading-recycler-view-images-in-androi
//https://github.com/rajtheinnovator/Foodie/blob/master/app/src/main/java/com/enpassio/foodie/RecipeWidget.java

//problem solved using:
//https://discussions.udacity.com/t/images-of-recipes/391736

//https://discussions.udacity.com/t/displaying-recipe-appropriate-images/404961