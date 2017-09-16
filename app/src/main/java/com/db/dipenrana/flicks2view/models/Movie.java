package com.db.dipenrana.flicks2view.models;

import com.db.dipenrana.flicks2view.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dipenrana on 9/12/17.
 */

public class Movie {
    public String getOriginalTitle() {
        return originalTitle;
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
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private String voteAverage;




    public Movie(JSONObject jsonObject) throws JSONException{
        this.originalTitle = jsonObject.getString("original_title");
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
               results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  results;
    }
}
