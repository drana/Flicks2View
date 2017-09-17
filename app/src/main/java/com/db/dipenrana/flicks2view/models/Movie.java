package com.db.dipenrana.flicks2view.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.db.dipenrana.flicks2view.utils.NetworkUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;



/**
 * Created by dipenrana on 9/12/17.
 */

public class Movie implements Parcelable{


    protected Movie(Parcel in) {
        originalTitle = in.readString();
        movieID = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {

        return (NetworkUtil.POSTER_PATH + posterPath);
    }

    public String getBackdropPath() {
        return (NetworkUtil.BACKDROP_PATH + backdropPath);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }




    private String originalTitle;
    private String movieID;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private String voteAverage;




    public Movie(JSONObject jsonObject) throws JSONException{
        this.originalTitle = jsonObject.getString("original_title");
        this.movieID = jsonObject.getString("id");
        this.overview= jsonObject.getString("overview");
        this.posterPath = jsonObject.getString("poster_path");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.releaseDate = jsonObject.getString("release_date");
        this.voteAverage = jsonObject.getString("vote_average");
    }


    public static ArrayList<Movie> GetMoviesfromJsonArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<Movie>();

        for (int x=0;x<array.length();x++){
            try {
                Movie movieItem = new Movie(array.getJSONObject(x));
                results.add(movieItem);
               //results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(originalTitle);
        parcel.writeString(movieID);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(releaseDate);
        parcel.writeString(voteAverage);

    }
}
