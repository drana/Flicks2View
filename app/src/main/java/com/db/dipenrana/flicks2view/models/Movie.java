package com.db.dipenrana.flicks2view.models;

/**
 * Created by dipenrana on 9/12/17.
 */

public class Movie {
    public String movieTitle;
    public String movieInfo;

    public Movie(String movieTitle, String movieInfo){
        this.movieInfo = movieInfo;
        this.movieTitle = movieTitle;
    }
}
