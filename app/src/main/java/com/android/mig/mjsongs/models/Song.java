package com.android.mig.mjsongs.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable{

    private String mCollectionName;
    private String mTrackName;
    private String mPreviewUrl;
    private String mArtworkUrl;
    private double mTrackPrice;
    private String mReleaseDate;
    private String mPrimaryGenreName;

    public Song(String collectionName, String trackName, String previewUrl, String artworkUrl, double trackPrice, String releaseDate, String primaryGenreName ){
        this.mCollectionName = collectionName;
        this.mTrackName = trackName;
        this.mPreviewUrl = previewUrl;
        this.mArtworkUrl = artworkUrl;
        this.mPreviewUrl = previewUrl;
        this.mArtworkUrl = artworkUrl;
        this.mTrackPrice = trackPrice;
        this.mReleaseDate = releaseDate;
        this.mPrimaryGenreName = primaryGenreName;
    }

    protected Song(Parcel in) {
        mCollectionName = in.readString();
        mTrackName = in.readString();
        mPreviewUrl = in.readString();
        mArtworkUrl = in.readString();
        mTrackPrice = in.readDouble();
        mReleaseDate = in.readString();
        mPrimaryGenreName = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getCollectionName() {
        return mCollectionName;
    }

    public void setCollectionName(String mCollectionName) {
        this.mCollectionName = mCollectionName;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String mTrackName) {
        this.mTrackName = mTrackName;
    }

    public String getPreviewUrl() {
        return mPreviewUrl;
    }

    public void setPreviewUrl(String mPreviewUrl) {
        this.mPreviewUrl = mPreviewUrl;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String mArtworkUrl) {
        this.mArtworkUrl = mArtworkUrl;
    }

    public double getTrackPrice() {
        return mTrackPrice;
    }

    public void setTrackPrice(double mTrackPrice) {
        this.mTrackPrice = mTrackPrice;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getPrimaryGenreName() {
        return mPrimaryGenreName;
    }

    public void setPrimaryGenreName(String mPrimaryGenreName) {
        this.mPrimaryGenreName = mPrimaryGenreName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCollectionName);
        parcel.writeString(mTrackName);
        parcel.writeString(mPreviewUrl);
        parcel.writeString(mArtworkUrl);
        parcel.writeDouble(mTrackPrice);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPrimaryGenreName);
    }
}
