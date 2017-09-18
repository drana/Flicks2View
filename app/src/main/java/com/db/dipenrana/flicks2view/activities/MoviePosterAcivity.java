package com.db.dipenrana.flicks2view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.db.dipenrana.flicks2view.R;

public class MoviePosterAcivity extends RecyclerView.ViewHolder  {

    private TextView tvTitle, tvInfo;
    private ImageView ivPosterImage;

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvInfo() {
        return tvInfo;
    }


    public ImageView getIvPosterImage() {
        return ivPosterImage;
    }

    public MoviePosterAcivity(View view) {
        super(view);
        tvTitle = (TextView) view.findViewById(R.id.txtview_MovieTitle);
        tvInfo = (TextView) view.findViewById(R.id.txtview_MovieInfo);
        ivPosterImage = (ImageView) view.findViewById(R.id.iv_moviePoster);
    }


}
