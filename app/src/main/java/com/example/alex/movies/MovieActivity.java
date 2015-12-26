package com.example.alex.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.movies.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {
    public static Movie moviesBuilder;
    private static TextView tvTitleRu;
    private static TextView tvTitleEn;
    private static TextView tvTxtFilm;
    private static ImageView imgTitleFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initIO();
    }

    private void initView(){
        tvTitleRu = (TextView) findViewById(R.id.tvTitleRu);
        tvTitleEn = (TextView) findViewById(R.id.tvTitleEn);
        tvTxtFilm = (TextView) findViewById(R.id.tvTxtFilm);
        imgTitleFilm = (ImageView) findViewById(R.id.imgTitleFilm);
    }

    public static void initIO(){
        tvTitleRu.setText(moviesBuilder.titleRu);
        tvTitleEn.setText(moviesBuilder.titleEn);
        tvTxtFilm.setText(moviesBuilder.txtFilm);
        Picasso.with(MainActivity.context).load(moviesBuilder.urlImg).into(imgTitleFilm);
    }



}
