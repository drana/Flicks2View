package com.db.dipenrana.flicks2view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.adapters.MoviesAdapter;
import com.db.dipenrana.flicks2view.models.Movie;
import com.db.dipenrana.flicks2view.utils.NetworkUtil;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> movies = new ArrayList<Movie>();
    MoviesAdapter moviesAdapter;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //adapter to convert list of movies to view
        moviesAdapter = new MoviesAdapter(this, movies);

        //attach listview to adpater
        lvItems = (ListView) findViewById(R.id.lvMovies);
        lvItems.setAdapter(moviesAdapter);

        //setup async http client
        String url = NetworkUtil.API_URL + NetworkUtil.API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
//        params.put("q", "android");
//        params.put("rsz", "8");
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    movies.addAll(Movie.GetMoviesfromJsonArray(response.getJSONArray("results")));
                    moviesAdapter.notifyDataSetChanged();
                    Log.d("Debug","httpclient get success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


}
