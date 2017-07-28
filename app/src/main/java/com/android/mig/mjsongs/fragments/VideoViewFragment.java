package com.android.mig.mjsongs.fragments;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.mig.mjsongs.R;

public class VideoViewFragment extends Fragment{

    private final String TAG = getClass().getName();

    VideoView mVideoView;
    MediaController mMediaController;
    View rootView;
    String songUrl;

    public VideoViewFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_song_player, container, false);

        songUrl = getArguments().getString(Intent.ACTION_MEDIA_SHARED);

        mVideoView = (VideoView) rootView.findViewById(R.id.song_video_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (mMediaController == null){
                mMediaController = new MediaController(getContext()){
                    @Override
                    public void hide() {
                        // prevents media controller from hiding
                    }
                };
            }
            mMediaController.setAnchorView(mVideoView);
            Uri mediaUri = Uri.parse(songUrl);

            mVideoView.setMediaController(mMediaController);
            mVideoView.setVideoURI(mediaUri);
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.start();
                mMediaController.show();
            }
        });

    }

    @Override
    public void onStop() {
        mVideoView.stopPlayback();
        mVideoView.seekTo(0);
        super.onStop();
    }
}
