package com.android.mig.mjsongs.models;

public class Song {

    String mCollectionName;
    String mTrackName;
    String mPreviewUrl;
    String mArtworkUrl;
    double mTrackPrice;
    String mReleaseDate;
    String mPrimaryGenreName;

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
}
