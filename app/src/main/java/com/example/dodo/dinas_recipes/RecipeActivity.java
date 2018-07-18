package com.example.dodo.dinas_recipes;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.example.dodo.dinas_recipes.Adapters.RecipeListAdapter;
import com.example.dodo.dinas_recipes.Fragments.Recipe_Detail_Fragment;
import com.example.dodo.dinas_recipes.Models.Recipes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeActivity extends AppCompatActivity implements RecipeListAdapter.ListItemClickListener{
    ArrayList<Recipes> recipeModel;
    @BindView(R.id.recipe_list)
    RecyclerView recyclerView;
    RecipeListAdapter recipeListAdapter;

    ArrayList<Recipes> recipeArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        //use a fragment manager and transaction to add the fragment to screeen (adds fragment to specifed container)
/*
        if (savedInstanceState == null) {
            // Then the application is not being reloaded
            // using a fragment transaction.
            // Restore last state for checked position.

            //create a new bodyparts fragment instance and display it using fragment manager:
            ReceipeFragment headfragment = new ReceipeFragment();

            //fragment transaction
            getSupportFragmentManager().beginTransaction().add(R.id.receipe_container, headfragment).commit();
        }

    }
*/
        recipeArrayList = new ArrayList<>();


        recipeListAdapter = new RecipeListAdapter(recipeArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(recipeListAdapter);

        recyclerView.setLayoutManager(linearLayoutManager);


//use volley library to send requests:
//https://github.com/dina789/Web1/blob/master/app/src/main/java/com/example/pc/web/MainActivity.java

//https://github.com/dina789/FinalProject-master-master-master-master/blob/master/app/src/main/java/com/example/zizo/myapplication/MainActivity.java


    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    //connectivity network info

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }


    @Override
    public void onListItemClick(Recipes recipes) {
        //bundle it up and attach it to a new activity that launches receipe detail activity

        Bundle bundle = new Bundle();
        bundle.putSerializable("key", recipeModel);
        Intent intent = new Intent(this, Recipe_Detail_Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);




    }
}


/**
 * Master detail flow tutorial (from mentor):
 * https://www.techotopia.com/index.php/An_Android_Master/Detail_Flow_Tutorial
 * another tutoriAL:
 * https://google-developer-training.gitbooks.io/android-developer-advanced-course-practicals/unit-1-expand-the-user-experience/lesson-1-fragments/1-2-p-communicating-with-a-fragment/1-2-p-communicating-with-a-fragment.html
 * passing  serializable list using Bundle.Serializable:
 * https://stackoverflow.com/questions/14333449/passing-data-through-intent-using-serializable
 * <p>
 * static and dynamic fragment:
 * https://stackoverflow.com/questions/23664906/static-fragments-vs-dynamic-fragments
 * <p>
 * setting cardview and recycler view:
 * https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
 * <p>
 * <p>
 * This way you’ll be able to send data between activities, and then from Activity to Fragment:
 * https://discussions.udacity.com/t/baking-app-help/775556
 * https://stackoverflow.com/questions/7149802/how-to-transfer-some-data-to-another-fragment
 * <p>
 * on how to use on saved instance:
 * https://stackoverflow.com/questions/6525698/how-to-use-onsavedinstancestate-example-please
 * <p>
 * check for loading data:
 * https://github.com/AKBwebdev/BakingApp/blob/master/app/src/main/java/com/example/android/bakingapp/fragments/RecipeListFragment.java
 * <p>
 * https://github.com/AbduallahAtta/BakingApp/tree/master/app/src/main/res/layout
 * https://github.com/matewiszt/AnnasBakery/blob/master/app/src/main/java/com/example/android/annasbakery/activity/DetailActivity.java
 * https://github.com/AKBwebdev/BakingApp/tree/master/app/src/main/java/com/example/android/bakingapp/network
 * https://developer.android.com/training/implementing-navigation/descendant#java
 * https://github.com/DimitriKatsoulis/Udacity_BakingApp/tree/master/app/src/main
 * https://github.com/ddeleon92/BakingApp/tree/master/app/src/main/java/com/example/daou5____/mybakingapp
 * https://github.com/dnKaratzas/udacity-baking-recipes/tree/master/app/src/main/java/eu/dkaratzas/bakingrecipes
 * <p>
 * for json data:: https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
 * <p>
 * important review on retrofit :
 * https://github.com/diamondCollector/BakingApp
 * https://discussions.udacity.com/t/retrofit-loading-intial-list-of-recipes/725377/7
 * <p>
 * for implementation reference:
 * http://www.i-programmer.info/professional-programmer/accreditation/10908-insiders-guide-to-udacity-android-developer-nanodegree-part-3-the-making-of-baking-app.html
 */

//note or consider that
// RecipeFragment won't be changing during the runtime of its host RecipeActivity,
//   we can consider it a Static Fragment,
//     which means that we can treat it and load it as a simple design time layout like every other.
//adding exoplayer:https://medium.com/@yusufcakmak/playing-audio-and-video-with-exoplayer-2-4f4c2c2d9772
//data binding: http://www.vogella.com/tutorials/AndroidDatabinding/article.html
//widget: https://discussions.udacity.com/t/baking-app-help/775556/20