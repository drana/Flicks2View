package com.db.dipenrana.flicks2view.models;

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
        return posterPath;
    }

    String originalTitle;
     String overview;
     String posterPath;


    public Movie(JSONObject jsonObject) throws JSONException{
        this.originalTitle = jsonObject.getString("original_title");
        this.overview= jsonObject.getString("overview");
        this.posterPath = jsonObject.getString("poster_path");
    }


    public static ArrayList<Movie> fromJsonArray(JSONArray array){
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
