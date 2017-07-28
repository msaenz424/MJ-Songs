package com.android.mig.mjsongs.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.android.mig.mjsongs.models.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenSongsJsonUtils {

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * This method retrieves data from a JSON string
     * and save it into an ArrayList of Song objects
     *
     * @param songJsonResponse a JSON string
     * @return an ArrayList of Song objects
     */
    public static ArrayList<Song> getSongArrayFromJson(String songJsonResponse) {

        final String M_RESULT = "results";

        final String M_COLLECTION_NAME = "collectionName";
        final String M_TRACK_NAME = "trackName";
        final String M_PREVIEW_URL = "previewUrl";
        final String M_ARTWORK_URL = "artworkUrl100";
        final String M_TRACK_PRICE = "trackPrice";
        final String M_RELEASE_DATE = "releaseDate";
        final String M_PRIMARY_GENRE_NAME = "primaryGenreName";

        ArrayList<Song> mSongArrayList = new ArrayList<>();

        try {
            JSONObject songJsonObject = new JSONObject(songJsonResponse);
            JSONArray songsJsonArray = songJsonObject.getJSONArray(M_RESULT);

            for (int i = 0; i < songsJsonArray.length(); i++){

                JSONObject resultJsonObject = songsJsonArray.getJSONObject(i);

                String collectionName = "";
                if (resultJsonObject.has(M_COLLECTION_NAME) && !resultJsonObject.isNull(M_COLLECTION_NAME)) {
                    collectionName = resultJsonObject.getString(M_COLLECTION_NAME);
                }
                String trackName = resultJsonObject.getString(M_TRACK_NAME);
                String previewUrl = resultJsonObject.getString(M_PREVIEW_URL);
                String artworkUrl = resultJsonObject.getString(M_ARTWORK_URL);
                double trackPrice = resultJsonObject.getDouble(M_TRACK_PRICE);
                String releaseDate = resultJsonObject.getString(M_RELEASE_DATE);
                String primaryGenreName = resultJsonObject.getString(M_PRIMARY_GENRE_NAME);

                Log.d("array", String.valueOf(i) + " " + trackName);
                Song mSongObject = new Song(
                        collectionName,
                        trackName,
                        previewUrl,
                        artworkUrl,
                        trackPrice,
                        releaseDate,
                        primaryGenreName
                );
                mSongArrayList.add(mSongObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mSongArrayList;
    }

    /** Retrieves the image as bitmap from url provided
     *
     *  @param imageUri the url from the image
     *  @return a bitmap of the image
     */
    public static Bitmap getBitMapFromUrl(String imageUri){
        HttpURLConnection connection = null;

        try {
            URL url = new URL(imageUri);
            connection= (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream is = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);

            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(connection!=null) {
                connection.disconnect();
            }
        }
    }
}
