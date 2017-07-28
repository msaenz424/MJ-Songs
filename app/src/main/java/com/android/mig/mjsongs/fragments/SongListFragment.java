package com.android.mig.mjsongs.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.mig.mjsongs.DetailsHandler;
import com.android.mig.mjsongs.R;
import com.android.mig.mjsongs.SongHandler;
import com.android.mig.mjsongs.activities.DetailsActivity;
import com.android.mig.mjsongs.models.Song;
import com.android.mig.mjsongs.adapters.SongsAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.android.mig.mjsongs.utils.OpenSongsJsonUtils.getResponseFromHttpUrl;
import static com.android.mig.mjsongs.utils.OpenSongsJsonUtils.getSongArrayFromJson;

public class SongListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<ArrayList<Song>>, SongHandler, DetailsHandler{

    private static final int LOADER_ID = 100;
    private final String MJ_URL = "https://itunes.apple.com/search?term=Michael+jackson";
    private URL songs_URL = null;

    private SongsAdapter mSongsAdapter;

    View rootView;

    public SongListFragment() {
        try {
            songs_URL = new URL(MJ_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_song_list, container, false);
        RecyclerView mSongsRecyclerView = (RecyclerView) rootView.findViewById(R.id.songs_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        mSongsRecyclerView.setLayoutManager(linearLayoutManager);

        mSongsAdapter = new SongsAdapter(this, this);
        mSongsRecyclerView.setAdapter(mSongsAdapter);

        getLoaderManager().initLoader(LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader<ArrayList<Song>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<Song>>(rootView.getContext()) {

            ArrayList<Song> mSongsArrayList = null;

            @Override
            protected void onStartLoading() {
                forceLoad();
            }

            @Override
            public ArrayList<Song> loadInBackground() {
                ArrayList<Song> resultArray = null;
                try {
                    String jsonResult = getResponseFromHttpUrl(songs_URL);
                    resultArray = getSongArrayFromJson(jsonResult);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return resultArray;
            }

            @Override
            public void deliverResult(ArrayList<Song> data) {
                mSongsArrayList = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Song>> loader, ArrayList<Song> data) {
        if (data != null){
            mSongsAdapter.setSongsAdapter(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Song>> loader) {

    }

    @Override
    public void onClick(String songUrl) {
        VideoViewFragment videoViewFragment = new VideoViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Intent.ACTION_MEDIA_SHARED, songUrl);
        videoViewFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.song_player_container,videoViewFragment).commit();
    }

    @Override
    public void onClick(Song song, ImageView imageView) {
        Bundle bundle = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // takes care of the shared element transition
             bundle = ActivityOptions
                    .makeSceneTransitionAnimation(getActivity(), imageView, imageView.getTransitionName())
                    .toBundle();
        }
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, song);
        startActivity(intent, bundle);
    }
}
