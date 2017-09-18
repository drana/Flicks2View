package com.db.dipenrana.flicks2view.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.activities.MovieBackdropActivity;
import com.db.dipenrana.flicks2view.activities.MoviePosterAcivity;
import com.db.dipenrana.flicks2view.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dipenrana on 9/17/17.
 */

public class ComplexRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    // Store a member variable for the contacts
    private ArrayList<Movie> mMovies;
    // Store the context for easy access
    private Context mContext;
    String imgURL;


    private final int POSTER = 0, LANDSCAPE = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComplexRecyclerViewAdapter(Context context,ArrayList<Movie> movies) {
        this.mMovies = movies;
        this.mContext = context;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }

    @Override
    public int getItemViewType(int position) {
        Movie details = mMovies.get(position);
        float rating = Float.parseFloat(details.getVoteAverage());
        if(rating >=5){
            return LANDSCAPE;
        }
        else
        {
            return POSTER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //More to come
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case POSTER:
                View vPoster = inflater.inflate(R.layout.activity_movie_poster_acivity, viewGroup, false);
                viewHolder = new MoviePosterAcivity(vPoster);
                break;
            case LANDSCAPE:
                View vLandscape = inflater.inflate(R.layout.activity_movie_backdrop, viewGroup, false);
                viewHolder = new MovieBackdropActivity(vLandscape);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_activated_1, viewGroup, false);
                viewHolder = new MoviePosterAcivity(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case POSTER:
                MoviePosterAcivity vh1 = (MoviePosterAcivity) viewHolder;
                configurePosterAcitivity(vh1, position);
                break;
            case LANDSCAPE:
                MovieBackdropActivity vh2 = (MovieBackdropActivity) viewHolder;
                configureLandscapeActivity(vh2, position);
                break;
            default:
                MoviePosterAcivity vh = (MoviePosterAcivity) viewHolder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configurePosterAcitivity(MoviePosterAcivity holder, int position) {
        // Get the data model based on position
        Movie movie = mMovies.get(position);

//        viewHolder.movieTitile.setText(movie.getOriginalTitle());
//        viewHolder.movieInfo.setText(movie.getOverview());
//        viewHolder.posterImage.setImageResource(0);

        // Set item views based on your views and data model
        TextView tvTitle = holder.getTvTitle();
        tvTitle.setText(movie.getOriginalTitle());
        TextView tvInfo = holder.getTvInfo();
        tvInfo.setText(movie.getOverview());
        ImageView ivImage = holder.getIvPosterImage();
        ivImage.setImageResource(0);

        int orientation = mContext.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            imgURL = movie.getPosterPath();
        }else {
            imgURL = movie.getBackdropPath();
        }

        Picasso.with(mContext)
                .load(imgURL)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.posterplaceholder)
                .error(R.drawable.postererror)
                .noFade()
                .transform(new RoundedCornersTransformation(10, 10))
                .into(holder.getIvPosterImage());
    }

    private void configureLandscapeActivity(MovieBackdropActivity holder1, int position) {

        // Get the data model based on position
        Movie movie = mMovies.get(position);

        ImageView ivImage = holder1.getIvMovieImage();
        ivImage.setImageResource(0);

        int orientation = mContext.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            imgURL = movie.getPosterPath();
        }else {
            imgURL = movie.getBackdropPath();
        }

        Picasso.with(mContext)
                .load(imgURL)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.posterplaceholder)
                .error(R.drawable.postererror)
                .noFade()
                .transform(new RoundedCornersTransformation(10, 10))
                .into(holder1.getIvMovieImage());

    }

    private void configureDefaultViewHolder(MoviePosterAcivity vh, int position) {

    }
}
