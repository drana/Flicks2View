package com.db.dipenrana.flicks2view.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dipenrana on 9/17/17.
 */

public class Genre {

    public String getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    private String genreId;
    private String genreName;

    public Genre(JSONObject jsonObject) throws JSONException {
        this.genreId = jsonObject.getString("id");
        this.genreName = jsonObject.getString("name");

    }

    public static ArrayList<Genre> GetGenresfromJsonArray(JSONArray array){
        ArrayList<Genre> results = new ArrayList<Genre>();

        for (int x=0;x<array.length();x++){
            try {
                results.add(new Genre(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  results;
    }

}