package com.example.alex.movies.parsed;

import android.os.AsyncTask;
import android.view.View;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.MovieActivity;
import com.example.alex.movies.models.Movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ParsedMovie {
    private static Element titleRu;
    private static Element titleEn;
    private static Element txtFilm;
    private static Element urlImg;

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
                titleRu = doc.select("b-player-skin__header-inner").first();
                titleEn = doc.select("b-player-skin__header-origin").first();
                txtFilm = doc.select("item-decription full").first();
                urlImg = doc.select("img").first();
                titleRus = titleRu.text();
                titleEng = titleEn.text();
                txtFilms = txtFilm.text();
                urlImgs = urlImg.text();
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
            MovieActivity.pbFilm.setVisibility(View.INVISIBLE);
            MovieActivity.classFilmDataBuilder = classFilmDataBuilder;
            mainActivity.initIOFilm();
        }
    }
}
