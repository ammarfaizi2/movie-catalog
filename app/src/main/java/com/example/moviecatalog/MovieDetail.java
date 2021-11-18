package com.example.moviecatalog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class MovieDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        TextView title = findViewById(R.id.movieTitleDetail);
        TextView overview = findViewById(R.id.movieOverviewDetail);
        TextView releaseDate = findViewById(R.id.movieReleaseDateDetail);
        title.setText(CustomAdapter.title_);
        overview.setText(CustomAdapter.overview_);
        releaseDate.setText(CustomAdapter.releaseDate_);
        new DownloadImageTask(findViewById(R.id.moviePicDetail)).execute(CustomAdapter.pic_);
    }
}
