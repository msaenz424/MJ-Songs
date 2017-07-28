package com.android.mig.mjsongs.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mig.mjsongs.DetailsHandler;
import com.android.mig.mjsongs.utils.FetchImageTask;
import com.android.mig.mjsongs.SongHandler;
import com.android.mig.mjsongs.R;
import com.android.mig.mjsongs.models.Song;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsAdapterViewHolder> {

    private SongHandler mSongHandler;
    private DetailsHandler mDetailsHandler;

    private ArrayList<Song> mSongsArrayList;
    private WeakReference<LinearLayout> mWeakReference;

    private static int lastPosition;

    public SongsAdapter(SongHandler songHandler, DetailsHandler detailsHandler){
        mSongsArrayList = new ArrayList<>();
        mSongHandler = songHandler;
        mDetailsHandler = detailsHandler;
    }

    public void setSongsAdapter(ArrayList<Song> songsArrayList){
        this.mSongsArrayList = songsArrayList;
        notifyDataSetChanged();
    }

    @Override
    public SongsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_song, parent, false);

        return new SongsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsAdapterViewHolder holder, int position) {
        String artworkUrl = mSongsArrayList.get(position).getArtworkUrl();
        FetchImageTask fetchImageTask = new FetchImageTask(holder.mImageViewArtwork);
        fetchImageTask.execute(artworkUrl);
        holder.mTextViewSongName.setText(mSongsArrayList.get(position).getTrackName());
    }

    @Override
    public int getItemCount() {
        return mSongsArrayList.size();
    }

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout mLinearLayout;
        ImageView mImageViewArtwork;
        TextView mTextViewSongName;
        Button mButtonPlaySong;

        public SongsAdapterViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.song_item_linear_layout);
            mImageViewArtwork = (ImageView) itemView.findViewById(R.id.artwork_image_view);
            mTextViewSongName = (TextView) itemView.findViewById(R.id.song_text_view);
            mButtonPlaySong = (Button) itemView.findViewById(R.id.play_button);
            mLinearLayout.setOnClickListener(this);
            mButtonPlaySong.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Song song = mSongsArrayList.get(getAdapterPosition());

            switch (view.getId()){
                case R.id.play_button:
                    if (lastPosition != getAdapterPosition() && mWeakReference != null) {
                        LinearLayout linearLayout = mWeakReference.get();
                        linearLayout.setSelected(false);
                    }
                    mLinearLayout.setSelected(true);

                    String songUrl = song.getPreviewUrl();
                    mSongHandler.onClick(songUrl);
                    lastPosition = getAdapterPosition();
                    mWeakReference = new WeakReference<>(mLinearLayout);
                    break;
                default:
                    mDetailsHandler.onClick(song, mImageViewArtwork);
                    break;
            }
        }
    }
}
