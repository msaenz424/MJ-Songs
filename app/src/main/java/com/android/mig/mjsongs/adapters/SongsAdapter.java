package com.android.mig.mjsongs.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mig.mjsongs.SongHandler;
import com.android.mig.mjsongs.R;
import com.android.mig.mjsongs.models.Song;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsAdapterViewHolder> {

    SongHandler mSongHandler;

    ArrayList<Song> mSongsArrayList;

    public SongsAdapter(SongHandler songHandler){
        mSongsArrayList = new ArrayList<>();
        mSongHandler = songHandler;
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
        //holder.mImageViewArtwork.setImageBitmap();
        holder.mTextViewSongName.setText(mSongsArrayList.get(position).getTrackName());
    }

    @Override
    public int getItemCount() {
        return mSongsArrayList.size();
    }

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mImageViewArtwork;
        TextView mTextViewSongName;
        Button mButtonPlaySong;

        public SongsAdapterViewHolder(View itemView) {
            super(itemView);
            mImageViewArtwork = (ImageView) itemView.findViewById(R.id.artwork_image_view);
            mTextViewSongName = (TextView) itemView.findViewById(R.id.song_text_view);
            mButtonPlaySong = (Button) itemView.findViewById(R.id.play_button);
            mButtonPlaySong.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String songUrl =mSongsArrayList.get(getAdapterPosition()).getPreviewUrl();
            mSongHandler.onClick(songUrl);
        }
    }
}
