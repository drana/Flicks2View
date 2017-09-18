package com.db.dipenrana.flicks2view.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.db.dipenrana.flicks2view.models.Movie;
import com.db.dipenrana.flicks2view.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dipenrana on 9/17/17.
 */

public class RecycleViewMoviesAdapter extends RecyclerView.Adapter<RecycleViewMoviesAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private ArrayList<Movie> mMovies;
    // Store the context for easy access
    private Context mContext;
    String imgURL;

    public Context getmContext() {
        return mContext;
    }

    //constructor
    public RecycleViewMoviesAdapter(Context context, ArrayList<Movie> movies) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //get context
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.item_movie_rv, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the data model based on position
        Movie movie = mMovies.get(position);

//        viewHolder.movieTitile.setText(movie.getOriginalTitle());
//        viewHolder.movieInfo.setText(movie.getOverview());
//        viewHolder.posterImage.setImageResource(0);

        // Set item views based on your views and data model
        TextView tvTitle = holder.movieTitile;
        tvTitle.setText(movie.getOriginalTitle());
        TextView tvInfo = holder.movieInfo;
        tvInfo.setText(movie.getOverview());
        ImageView ivImage = holder.posterImage;
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
                .into(holder.posterImage);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
        //return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView movieTitile;
        TextView movieInfo;
        ImageView posterImage;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            movieTitile = (TextView) itemView.findViewById(R.id.txtview_MovieTitle);
            movieInfo = (TextView) itemView.findViewById(R.id.txtview_MovieInfo);
            posterImage = (ImageView) itemView.findViewById(R.id.iv_moviePoster);

        }

    }

}
