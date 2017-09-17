package com.db.dipenrana.flicks2view.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.models.Movie;
import com.db.dipenrana.flicks2view.utils.NetworkUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;


public class MovieDetails extends AppCompatActivity {

    JSONArray genres;
    AsyncHttpClient client;
    Movie movieDetail;
    private TextView title;
    private TextView overview;
    private TextView ratings;
    private TextView genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        //change actionbar style
        UpdateActionBar();

        Intent i = getIntent();
        movieDetail = (Movie) i.getParcelableExtra("MOVIE_DETAIL");

        client = new AsyncHttpClient();
        GetMovieGenres();

        //setup movie details
        SetupMovieDetail(movieDetail);


    }

    private void UpdateActionBar() {
        // in Activity#onCreate
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_titile);

        //set font and color for actiona bar
        TextView title = (TextView) findViewById(R.id.actionBarTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        title.setTypeface(font);
    }


    public void GetMovieGenres() {

        //AsyncHttpClient client = new AsyncHttpClient();

        client.get(NetworkUtil.GENRE_PATH, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    genres = response.getJSONArray("genres");
                    genres.notify();
                    Log.d("Debug","httpclient get success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void SetupMovieDetail(Movie item) {

        title = (TextView) findViewById(R.id.txtview_MovieTitle);
        overview = (TextView) findViewById(R.id.movieOverview);
        ratings = (TextView) findViewById(R.id.avgRating);
        genre = (TextView) findViewById(R.id.genres);

        title.setText(item.getOriginalTitle()+"("+item.getReleaseDate()+")");
        ratings.setText(item.getVoteAverage()+"/10");
        //genre.setText(item.getGenreIDs().toString());




    }
}
