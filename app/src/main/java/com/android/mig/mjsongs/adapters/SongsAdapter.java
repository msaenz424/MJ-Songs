package com.android.mig.mjsongs.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsAdapterViewHolder> {

    SongHandler mSongHandler;
    DetailsHandler mDetailsHandler;

    ArrayList<Song> mSongsArrayList;

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

        LinearLayout mConstraintLayout;
        ImageView mImageViewArtwork;
        TextView mTextViewSongName;
        Button mButtonPlaySong;

        public SongsAdapterViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout = (LinearLayout) itemView.findViewById(R.id.song_item_linear_layout);
            mImageViewArtwork = (ImageView) itemView.findViewById(R.id.artwork_image_view);
            mTextViewSongName = (TextView) itemView.findViewById(R.id.song_text_view);
            mButtonPlaySong = (Button) itemView.findViewById(R.id.play_button);
            mConstraintLayout.setOnClickListener(this);
            mButtonPlaySong.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Song song = mSongsArrayList.get(getAdapterPosition());
            switch (view.getId()){
                case R.id.play_button:
                    String songUrl = song.getPreviewUrl();
                    mSongHandler.onClick(songUrl);
                    break;
                default:
                    mDetailsHandler.onClick(song, mImageViewArtwork);
                    break;
            }
        }
    }
}
