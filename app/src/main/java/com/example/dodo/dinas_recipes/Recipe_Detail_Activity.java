package com.example.dodo.dinas_recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.dodo.dinas_recipes.Fragments.Recipe_Detail_Fragment;
import com.example.dodo.dinas_recipes.Fragments.Video_Description_Fragment;
import com.example.dodo.dinas_recipes.Models.Ingredients;
import com.example.dodo.dinas_recipes.Models.Recipes;
import com.example.dodo.dinas_recipes.Models.Steps;

import java.util.ArrayList;
/*
 * Activity which shows the List of all ingredients and steps in the recipe for phone and on tablet it will show a multipane layout with step details and video.
 * */

public class Recipe_Detail_Activity extends AppCompatActivity implements Recipe_Detail_Fragment.OnStepClickListener {
    private static final String TAG = Recipe_Detail_Activity.class.getSimpleName();

    Recipes recipe;
    Recipe_Detail_Fragment ingredientsStepsFragment;
    Video_Description_Fragment videoAndDescriptionFragment;


    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
   // private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe__detail_);

        ArrayList<Ingredients>   ingredientArrayList = recipe.ingredientsArrayList;
        ArrayList<Steps> stepsArrayList = recipe.stepsArrayList;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();

    ingredientsStepsFragment = new Recipe_Detail_Fragment();
    ingredientsStepsFragment.setArguments(bundle);

    recipe = (Recipes) bundle.getSerializable("key");
    getSupportActionBar().setTitle(recipe.getName());

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_details,ingredientsStepsFragment).commit();


        // Determine if you're creating a two-pane or single-pane display// to check which layout is going to be loaded?// if tablet size check https://stackoverflow.com/questions/9279111/determine-if-the-device-is-a-smartphone-or-tablet
        if (findViewById(R.id.fragment_details) !=null){
// In two-pane mode, add initial BodyPartFragments to the screen

         //   mTwoPane = true;
            if (savedInstanceState == null) {


            videoAndDescriptionFragment = new Video_Description_Fragment();
                //   Bundle bundleVideo = new Bundle();
                //   bundleVideo.putString("Step URL",stepsArrayList.get(0).getVideoURL());
                //  bundleVideo.putString("Step Description",stepsArrayList.get(0).getDescription());
                //  videoAndDescriptionFragment.setArguments(bundleVideo);

            android.support.v4.app.FragmentTransaction videoFragmentTransaction = getSupportFragmentManager().beginTransaction();
            videoFragmentTransaction.add(R.id.fragment_video_description,videoAndDescriptionFragment).commit();
            //StepDetailFragment stepDetailFragment = new StepDetailFragment();
                //            stepDetailFragment. setStepsModelList(stepsModel);
                //            getSupportFragmentManager().beginTransaction()
                //                    .replace(R.id.detail_container, stepDetailFragment)
                //                    .commit();
        }





    }
    /*

    @Override
    public void onIngredientStepItemClicked(Bundle bundle) {
        //For phone
        if (findViewById(R.id.fragment_video_description) ==null){
            Intent GotoStepVideo = new Intent(this,VideosStepsDescriptionActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            //for tablets load fragment in two pane modes.
            videoAndDescriptionFragment = new Video_Description_Fragment();
            videoAndDescriptionFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction videoFragmentTransaction = getSupportFragmentManager().beginTransaction();

            videoFragmentTransaction.replace(R.id.fragment_video_description,videoAndDescriptionFragment).commit();
        }


    }

    } */}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnStepitemClicked(Bundle bundle) {
        //For phone
        if (findViewById(R.id.fragment_video_description) ==null){
            Intent GotoStepVideo = new Intent(this,VideosStepsDescriptionActivity.class);

            GotoStepVideo.putExtras(bundle);
            startActivity(GotoStepVideo);
        }else{
            //for tablets load fragment in two pane modes.
            videoAndDescriptionFragment = new Video_Description_Fragment();
            videoAndDescriptionFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction videoFragmentTransaction = getSupportFragmentManager().beginTransaction();

            videoFragmentTransaction.replace(R.id.fragment_video_description,videoAndDescriptionFragment).commit();
        }
    }
}


/**
 * using butterknife:
 * http://jakewharton.github.io/butterknife/
 * <p>
 * meaning of addtobackstack
 * https://stackoverflow.com/questions/22984950/what-is-the-meaning-of-addtobackstack-with-null-parameter
 * <p>
 * <p>
 * //OnBackPressed: https://stackoverflow.com/questions/26693754/fragment-addtobackstack-and-popbackstackimmediate-not-working*
 * //https://github.com/agungaprianto/Baking-app-revision/blob/master/app/src/main/java/id/developer/agungaprian/bakingapprevisi2/ui/ListDetailRecipeActivity.java
 * //https://stackoverflow.com/questions/27659038/addtobackstack-method-is-not-working-without-overriding-the-onbackpressed-me/27684596
 * //https://github.com/nikosvaggalis/Udacity-Advanced-Developer-Nanodegree-Baking-App-2017/blob/master/app/src/main/java/com/example/android/recipe/ui/RecipeDetailActivity.java
 */


//room
//https://github.com/tpakis/BakingApp/tree/master/app/src/main/java/com/scholarship/udacity/aithanasakis/bakingapp


//bundle information

//on clck launches next activity:



/*handling two pane mode:
//https://github.com/udacity/Android_Me/blob/TFragments.07-Solution-TwoPaneUI/app/src/main/java/com/example/android/android_me/ui/MainActivity.java#L67*/