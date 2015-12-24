package com.example.alex.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {
    public static com.example.alex.movies.models.Movie classFilmDataBuilder;
    private static TextView tvTitleRu;
    private static TextView tvTitleEn;
    private static TextView tvTxtFilm;
    private static ImageView imgTitleFilm;
    public static ProgressBar pbFilm;

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
        pbFilm = (ProgressBar) findViewById(R.id.pbFilm);
    }

    public static void initIO(){
        tvTitleRu.setText(classFilmDataBuilder.titleRu);
        tvTitleEn.setText(classFilmDataBuilder.titleEn);
        tvTxtFilm.setText(classFilmDataBuilder.txtFilm);
        Picasso.with(MainActivity.context).load(classFilmDataBuilder.urlImg).into(imgTitleFilm);
    }



}
