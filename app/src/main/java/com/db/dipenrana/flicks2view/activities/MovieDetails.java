package com.db.dipenrana.flicks2view.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.models.Genre;
import com.db.dipenrana.flicks2view.models.Movie;
import com.db.dipenrana.flicks2view.utils.NetworkUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class MovieDetails extends AppCompatActivity {


    Movie movieDetail;
    private TextView title;
    private TextView overview;
    private TextView ratings;
    private TextView genre;
    private ImageView bckdropImage;
    private ImageView posterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        //change actionbar style
        UpdateActionBar();

        Intent i = getIntent();
        movieDetail = (Movie) i.getParcelableExtra("MOVIE_DETAIL");

        //setup movie details
        try {
            SetupMovieDetail(movieDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }


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


    private void SetupMovieDetail(Movie item) throws JSONException {
        ArrayList<Movie> results = new ArrayList<Movie>();

        title = (TextView) findViewById(R.id.txtview_MovieTitle);
        overview = (TextView) findViewById(R.id.txtview_MovieInfo);
        ratings = (TextView) findViewById(R.id.avgRating);
        genre = (TextView) findViewById(R.id.genres);
        bckdropImage = (ImageView) findViewById(R.id.movieBackDrop);
        posterImage = (ImageView) findViewById(R.id.iv_moviePoster);

        title.setText(item.getOriginalTitle()+"("+item.getReleaseDate()+")");
        ratings.setText("Ratings: "+item.getVoteAverage()+"/10");
        overview.setText(item.getOverview());


        Picasso.with(this)
                .load(item.getBackdropPath())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.posterplaceholder)
                .error(R.drawable.postererror)
                .noFade()
                .transform(new RoundedCornersTransformation(10, 10))
                .into(bckdropImage);

        Picasso.with(this)
                .load(item.getPosterPath())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.posterplaceholder)
                .error(R.drawable.postererror)
                .noFade()
                .transform(new RoundedCornersTransformation(10, 10))
                .into(posterImage);




    }
}
