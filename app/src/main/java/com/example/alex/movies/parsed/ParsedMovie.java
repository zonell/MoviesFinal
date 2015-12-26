package com.example.alex.movies.parsed;

import android.os.AsyncTask;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.MovieActivity;
import com.example.alex.movies.models.Movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParsedMovie {
    private static String titleRus;
    private static String titleEng;
    private static String txtFilms;
    private static String urlImgs;

    public static void initFilmData(String url){
        new NewThreadParsed(url).execute();
    }

    private static class NewThreadParsed extends AsyncTask<Movie, Void, Movie>{
        private String url;

        public NewThreadParsed(String url) {
            this.url = url;
        }

        @Override
        protected Movie doInBackground(Movie... params) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                titleRus  = doc.select( "div.b-player-skin__header-inner > span").text();
                titleEng = doc.select("b-player-skin__header-origin").text();
                txtFilms = doc.select("p").text();
                urlImgs = doc.select("img").attr("src");

            } catch (IOException e) {
                e.printStackTrace();
            }
            Movie classFilmDataBuilder = new Movie(titleRus, titleEng, txtFilms, urlImgs);

            return classFilmDataBuilder;
        }

        @Override
        protected void onPostExecute(Movie classFilmDataBuilder) {
            MainActivity mainActivity = new MainActivity();
            super.onPostExecute(classFilmDataBuilder);
            MovieActivity.moviesBuilder = classFilmDataBuilder;
            mainActivity.initIOFilm();
        }
    }
}



