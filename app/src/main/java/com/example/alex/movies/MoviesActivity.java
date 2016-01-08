package com.example.alex.movies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.movies.models.Movie;
import com.squareup.picasso.Picasso;

public class MoviesActivity extends AppCompatActivity {
    public static Movie moviesBuilder;
    private static TextView tvTitleRu;
    private static TextView tvTxtFilm;
    private static TextView tvLike;
    private static TextView tvUnlike;
    private static ImageView imgTitleFilm;
    private static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        initView();
        initIO();
    }

    private void initView(){
        tvTitleRu = (TextView) findViewById(R.id.tvTitleRu);
        tvLike = (TextView) findViewById(R.id.tvLike);
        tvUnlike = (TextView) findViewById(R.id.tvUnlike);
        tvTxtFilm = (TextView) findViewById(R.id.tvTxtFilm);
        imgTitleFilm = (ImageView) findViewById(R.id.imgTitleFilm);
        data = (TextView) findViewById(R.id.tvDataCategories);
    }

    public static void initIO(){
        tvTitleRu.setText(moviesBuilder.titleRu);
        tvLike.setText(moviesBuilder.like);
        tvUnlike.setText(moviesBuilder.unlike);
        tvTxtFilm.setText(moviesBuilder.txtFilm);
        data.setText(moviesBuilder.data);
        Picasso.with(MainActivity.context).load(moviesBuilder.urlImg).into(imgTitleFilm);
    }

    public void onViewMovies(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(moviesBuilder.urlMovies));
        startActivity(intent);
    }
}
