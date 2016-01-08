package com.example.alex.movies.parsed;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.MoviesActivity;
import com.example.alex.movies.models.Categories;
import com.example.alex.movies.models.Movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParsedMovie {
    private static String titleRus;
    private static String txtFilms;
    private static String like;
    private static String unlike;
    private static String urlImgs;
    private static String data;
    private static String urlMovies;

    public static void initFilmData(String url, Categories categories){
        new NewThreadParsed(url, categories).execute();
    }

    private static class NewThreadParsed extends AsyncTask<Movie, Void, Movie>{
        private String url;
        private Categories categories;

        public NewThreadParsed(String url, Categories categories) {
            this.url = url;
            this.categories = categories;
        }

        @Override
        protected Movie doInBackground(Movie... params) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                titleRus = categories.title;
                like = categories.like;
                unlike = categories.unlike;
                txtFilms = doc.select("p").first().text();
                urlImgs = categories.urlImg;
                data = categories.data;
                urlMovies ="http://fs.to" + doc.select("div.b-view-material > a[href]").attr("href").toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Movie moviesBuilder = new Movie(titleRus, like, unlike, txtFilms, urlImgs, data, urlMovies);

            return moviesBuilder;
        }

        @Override
        protected void onPostExecute(Movie moviesBuilder) {
            super.onPostExecute(moviesBuilder);
            MoviesActivity.moviesBuilder = moviesBuilder;
            Intent intent = new Intent(MainActivity.context, MoviesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(intent);
        }
    }
}



