package com.android.mig.mjsongs.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mig.mjsongs.R;
import com.android.mig.mjsongs.models.Song;
import com.android.mig.mjsongs.utils.FetchImageTask;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView mTextViewCollectionName = (TextView) findViewById(R.id.collection_name_text_view);
        TextView mTextViewTrackName = (TextView) findViewById(R.id.track_name_text_view);
        ImageView mImageViewArtwork = (ImageView) findViewById(R.id.det_artwork_image_view);
        TextView mTextViewPrice = (TextView) findViewById(R.id.track_price_text_view);
        TextView mTextViewReleaseDate = (TextView) findViewById(R.id.release_date_text_view);
        TextView mTextViewPrimaryGenreName = (TextView) findViewById(R.id.genre_text_view);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Intent.EXTRA_TEXT)) {
                // retrieves the data passed from main activity
                Song mSong = intent.getParcelableExtra(Intent.EXTRA_TEXT);
                String mCollectionName = mSong.getCollectionName();
                String mTrackName = mSong.getTrackName();
                String mArtworkUrl = mSong.getArtworkUrl();
                double mTrackPrice = mSong.getTrackPrice();
                String mReleaseDate = mSong.getReleaseDate();
                String mPrimaryGenreName = mSong.getPrimaryGenreName();

                // set the data retrieved to the views
                mTextViewCollectionName.setText(mCollectionName);
                mTextViewTrackName.setText(mTrackName);
                FetchImageTask fetchImageTask = new FetchImageTask(mImageViewArtwork);
                fetchImageTask.execute(mArtworkUrl);
                mTextViewPrice.setText(String.valueOf(mTrackPrice));
                mTextViewReleaseDate.setText("(" + mReleaseDate.substring(0,4) + ")");
                mTextViewPrimaryGenreName.setText(mPrimaryGenreName);
            }
        }
    }
}
