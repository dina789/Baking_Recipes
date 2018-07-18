package com.example.dodo.dinas_recipes;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import com.example.dodo.dinas_recipes.Fragments.Video_Description_Fragment;
import com.example.dodo.dinas_recipes.Models.Steps;

public class VideosStepsDescriptionActivity extends AppCompatActivity {
    Video_Description_Fragment videoAndDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_steps_description);


        //create a new fragment only when an instance of fragment does not exists
        // Checks the orientation of the screen
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
        }else{
            getActionBar().show();
            //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }






        if (savedInstanceState == null) {
            Intent intent = getIntent();
            //passing data to the fragment
            Bundle bundle = intent.getBundleExtra(Intent.EXTRA_TEXT);
            Steps stepsModel = (Steps) bundle.getSerializable("ser");

            Video_Description_Fragment stepDetailFragment = new   Video_Description_Fragment();
            stepDetailFragment.setStepsModel(stepsModel);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_video_description, stepDetailFragment)
                    .commit();
        }
    }











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





    }
    //https://discussions.udacity.com/t/starting-previous-and-next-step/396361/62
//  https://stackoverflow.com/questions/11856886/hiding-title-bar-notification-bar-when-device-is-oriented-to-landscape
//https://stackoverflow.com/questions/49489532/how-to-hide-the-actionbar-and-make-the-videoview-set-to-full-screen