package com.example.dodo.dinas_recipes.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dodo.dinas_recipes.Models.Steps;
import com.example.dodo.dinas_recipes.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by srv_twry on 4/6/17.
 * The fragment which plays the associated video and shows the detailed description associated with a single step.
 */

public class Video_Description_Fragment extends Fragment {


    @BindView(R.id.tv_description_step)
    TextView descriptionStep;
    @BindView(R.id.tv_title_description_step)
    TextView tv_title_description_step;
    @BindView(R.id.view_video_step)
    SimpleExoPlayerView mPlayerView;
    String description_step;
    private Steps stepsModel;
    private SimpleExoPlayer mExoPlayer;

    public Video_Description_Fragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.video_description_exoplayer, container, false);

        ButterKnife.bind(this, rootView);

        //get the data from the parent activity about the url and the description
        //   Bundle bundle = getArguments();
        //    videoUrl = bundle.getString("Step URL");
        //   description_step = bundle.getString("Step Description");
        //   thumbnailUrl = bundle.getString("Step thumbnail");

        //Only for portrait-phone and tablet
        if (rootView.findViewById(R.id.tv_title_description_step) != null) {

            if (!description_step.equals("")) {
                descriptionStep.setText(stepsModel.getDescription());

                //   descriptionStep.setText(  description_step );
            } else {
                tv_title_description_step.setVisibility(View.GONE);
            }

            if (savedInstanceState != null) {
                stepsModel = (Steps) savedInstanceState.getSerializable("ser");

                if (!stepsModel.getVideoURL().equals("")) {
                    //Initialize the media player

                    initializePlayer(Uri.parse(stepsModel.getVideoURL()));

                    //For Landscape mode with no video, we will show a toast as their won't be any description too.
                    if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        Toast toast = Toast.makeText(getContext(), "No Video Available", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    mPlayerView.setVisibility(View.GONE);

                }

            }
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    /**
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), "Baking video");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    /**
     * Release ExoPlayer.
     */

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer = null;
        }
    }



    /*



    /**
     * Method that is called when the ExoPlayer state changes. Used to update the MediaSession
     * PlayBackState to keep in sync, and post the media notification.
     * @param playWhenReady true if ExoPlayer is playing, false if it's paused.
     * @param playbackState int describing the state of ExoPlayer. Can be STATE_READY, STATE_IDLE,
     *                      STATE_BUFFERING, or STATE_ENDED.
     */
    /*
    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());
    //    showNotification(mStateBuilder.build());
    }
*/


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("ser", stepsModel);

    }


    public void setStepsModel(Steps stepsModel) {

        this.stepsModel = stepsModel;
    }
}


//https://stackoverflow.com/questions/9156406/whats-the-difference-between-detaching-a-fragment-and-removing-it








