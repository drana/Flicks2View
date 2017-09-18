package com.db.dipenrana.flicks2view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.db.dipenrana.flicks2view.R;

public class MovieBackdropActivity extends RecyclerView.ViewHolder {


    private ImageView ivMovieImage;

    public ImageView getIvMovieImage() {
        return ivMovieImage;
    }

    public MovieBackdropActivity(View v) {
        super(v);
        ivMovieImage = (ImageView) v.findViewById(R.id.movieBackDrop);
    }
}
