package com.db.dipenrana.flicks2view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.adapters.MoviesAdapter;
import com.db.dipenrana.flicks2view.models.Movie;

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
    }
}
