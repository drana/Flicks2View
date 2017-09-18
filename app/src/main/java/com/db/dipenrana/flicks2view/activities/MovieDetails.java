package com.db.dipenrana.flicks2view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class MovieDetails extends AppCompatActivity {


    Movie movieDetail;
    private TextView title;
    private TextView overview;
    //private TextView ratings;
    private RatingBar ratings;
    private TextView genre;
    private ImageView bckdropImage;
    private ImageView posterImage;
    private TextView date;
    List<String> genreString = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        //change actionbar style
        UpdateActionBar();

        Intent i = getIntent();
        //String[] temp = i.getParcelableExtra("Genres");
        movieDetail = (Movie) i.getParcelableExtra("MOVIE_DETAIL");


        //setup movie details
        try {
            SetupMovieDetail(movieDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
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


    private void SetupMovieDetail(Movie item) throws JSONException, IOException {
        ArrayList<Movie> results = new ArrayList<Movie>();

        title = (TextView) findViewById(R.id.txtview_MovieTitle);
        overview = (TextView) findViewById(R.id.txtview_MovieInfo);
        //ratings = (TextView) findViewById(R.id.avgRating);
        ratings = (RatingBar) findViewById(R.id.avgRating);

        LayerDrawable stars = (LayerDrawable) ratings.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.primary_text) , PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.primary_text), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.primary_text), PorterDuff.Mode.SRC_ATOP);
        genre = (TextView) findViewById(R.id.genres);
        bckdropImage = (ImageView) findViewById(R.id.movieBackDrop);
        posterImage = (ImageView) findViewById(R.id.iv_moviePoster);
        date = (TextView) findViewById(R.id.dateReleased);

        //title.setText(item.getOriginalTitle()+"("+item.getReleaseDate()+")");
        title.setText(item.getOriginalTitle());
        date.setText(item.getReleaseDate());

        float rate = Float.parseFloat(item.getVoteAverage());
        ratings.setRating(rate/2);
        //ratings.setText("Ratings: "+item.getVoteAverage()+"/10");
        overview.setText(item.getOverview());

        String genreString =  GetGenresfromStringList(item.getGenreList());
        genre.setText(genreString);



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

    private String GetGenresfromStringList(List<String> genreList) throws IOException, JSONException {

      HashMap<String,String> allMovieGenre=  MovieGenre();

        List<String> genreString = new ArrayList<String>();
        String txtGenreString = "";

      for(int x=0; x < genreList.size(); x++){

         if(allMovieGenre.containsKey(genreList.get(x))){
             String temp = allMovieGenre.get(genreList.get(x));
             txtGenreString = temp + " " + txtGenreString;
             genreString.add(temp);
             int test =0;
         }
      }

return txtGenreString;
    }

    //get list of genres from tmdb database.
    private  HashMap<String,String> MovieGenre() throws IOException, JSONException
    {
        InputStream stream = getAssets().open("MovieGenre.json");
        int size = stream.available();
        byte[] buffer = new byte[size];
        stream.read(buffer);
        stream.close();
        String genreJson = new String(buffer, "UTF-8");
        JSONObject genresObj = new JSONObject(genreJson);
        JSONArray genreArray = genresObj.getJSONArray("genres");

        HashMap<String,String> genreList = new HashMap<String,String>();
        for(int i = 0; i < genreArray.length(); i++){
            String id = genreArray.getJSONObject(i).getString("id");
            String name = genreArray.getJSONObject(i).getString("name");
            genreList.put(id, name);

        }

        //return genresObj.getJSONObject("genres");
        return genreList;
    }




}
