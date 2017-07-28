package com.android.mig.mjsongs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.android.mig.mjsongs.utils.OpenSongsJsonUtils;

import java.lang.ref.WeakReference;

public class FetchImageTask extends AsyncTask<String, Void, Bitmap> {

    private WeakReference<ImageView> mWeakReference;

    public FetchImageTask(ImageView imageView){
        mWeakReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        return OpenSongsJsonUtils.getBitMapFromUrl(urls[0]);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if(mWeakReference != null && result != null){
            ImageView imageView = mWeakReference.get();

            if(imageView != null){
                imageView.setImageBitmap(result);
            }
        }
    }
}
