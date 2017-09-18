package com.db.dipenrana.flicks2view.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.adapters.MoviesAdapter;
import com.db.dipenrana.flicks2view.adapters.RecycleViewMoviesAdapter;
import com.db.dipenrana.flicks2view.models.Movie;
import com.db.dipenrana.flicks2view.utils.ItemClickSupport;
import com.db.dipenrana.flicks2view.utils.NetworkUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RecycleViewMovieActvity extends AppCompatActivity {

    private ArrayList<Movie> movies = new ArrayList<Movie>();
    RecycleViewMoviesAdapter rvmoviesAdapter;
    RecyclerView rvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_movie_actvity);


        //change actionbar title style
        UpdateActionBar();

//        //adapter to convert list of movies to view
        rvmoviesAdapter = new RecycleViewMoviesAdapter(this, movies);
//
//        //attach listview to adpater
        rvItems = (RecyclerView) findViewById(R.id.rvMovies);
        rvItems.setAdapter(rvmoviesAdapter);
        // Set layout manager to position the items
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        //setup async http client
        ConnectHttpClient();

        //setup on click listner for listview items
        setupListViewListener();
    }

    private void setupListViewListener() {

        ItemClickSupport.addTo(rvItems).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // do it
                        // first parameter is the context, second is the class of the activity to launch
                        Movie details = movies.get(position);
                        Intent intent = new Intent(RecycleViewMovieActvity.this,MovieDetails.class);
                        intent.putExtra("MOVIE_DETAIL", details );
                        //intent.putExtra("Genres", (Parcelable) details.getGenreList());
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                }
        );
    }

    //setup async http client
    private void ConnectHttpClient() {
        String url = NetworkUtil.API_URL + NetworkUtil.API_KEY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    movies.addAll(Movie.GetMoviesfromJsonArray(response.getJSONArray("results")));
                    rvmoviesAdapter.notifyDataSetChanged();
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

    private void UpdateActionBar() {
        // in Activity#onCreate
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_titile);

        //set font and color for actiona bar
        TextView title = (TextView) findViewById(R.id.actionBarTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        title.setTypeface(font);
    }
}
