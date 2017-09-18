package com.db.dipenrana.flicks2view.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.db.dipenrana.flicks2view.R;
import com.db.dipenrana.flicks2view.models.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dipenrana on 9/12/17.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {

    String imgURL;
    //lookup cache
    private static class ViewHolder{
        TextView movieTitile;
        TextView movieInfo;
        ImageView posterImage;

    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context,0,movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = getItem(position);

        //
        ViewHolder viewHolder; //lookup cache object
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder.movieTitile = (TextView) convertView.findViewById(R.id.txtview_MovieTitle);
            viewHolder.movieInfo = (TextView) convertView.findViewById(R.id.txtview_MovieInfo);
            viewHolder.posterImage = (ImageView) convertView.findViewById(R.id.iv_moviePoster);

            //cache viewHolder Object inside convertview
            convertView.setTag(viewHolder);
        }
        else {
            //retrieve the viewHolder from convertView to be recycled.
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.movieTitile.setText(movie.getOriginalTitle());
        viewHolder.movieInfo.setText(movie.getOverview());
        viewHolder.posterImage.setImageResource(0);

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            imgURL = movie.getPosterPath();
        }else {
            imgURL = movie.getBackdropPath();
        }

        Picasso.with(getContext())
                .load(imgURL)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.posterplaceholder)
                .error(R.drawable.postererror)
                .noFade()
                .transform(new RoundedCornersTransformation(10, 10))
                .into(viewHolder.posterImage);


        // Return the completed view to render on screen
        return convertView;
    }
}
