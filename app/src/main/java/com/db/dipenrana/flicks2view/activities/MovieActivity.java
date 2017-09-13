package com.db.dipenrana.flicks2view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.adapters.MoviesAdapter;
import com.db.dipenrana.flicks2view.models.Movie;
import com.db.dipenrana.flicks2view.utils.NetworkUtil;
import com.loopj.android.http.*;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //data source
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie sample = new Movie("Speed","This is a movie about fast moving bus.");
        movies.add(sample);
        movies.add(new Movie("Speed2","This is a movie about fast moving boat"));

        //adapter to convert list of movies to view
        MoviesAdapter moviesAdapter = new MoviesAdapter(this, movies);

        //attach listview to adpater
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        lvMovies.setAdapter(moviesAdapter);


        //setup async http client https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed
        String url = NetworkUtil.API_URL + "/now_playing?api_key=" + NetworkUtil.API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("q", "android");
        params.put("rsz", "8");
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
