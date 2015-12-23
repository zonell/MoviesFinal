package com.example.alex.movies.parsed;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.adapter.MoviesAdapter;
import com.example.alex.movies.models.AllMovies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsedAllMovies {
    private static Elements titleTxt;
    private static Element imgUrl;

    private static ArrayList<String> titleList = new ArrayList<>();
    private static ArrayList<String> urlList = new ArrayList<>();

    public static MoviesAdapter filmAdapter = new MoviesAdapter();

    public static void initializeData(String url) {
        new NewTreadParsed(url).execute();
    }

    public static class NewTreadParsed extends AsyncTask<List<AllMovies>, Void, List<AllMovies>> {
        private String url;

        public NewTreadParsed(String url) {
            this.url = url;
        }

        @Override
        protected List<AllMovies> doInBackground(List<AllMovies>... params) {
            Document docTxt;
            List<AllMovies> categories = new ArrayList<>();
            try {
                docTxt = Jsoup.connect(url).get();
                titleTxt = docTxt.select(".titlefilm");
                titleList.clear();
                urlList.clear();

                for (Element contents : titleTxt) {
                    titleList.add(contents.text());
                }
                for (int i = 6; i < titleList.size()+6; i++) {
                    imgUrl = docTxt.select("img").get(i);
                    urlList.add(imgUrl.attr("abs:src"));
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            for (int i = 0; i < titleList.size(); i++) {
                categories.add(new AllMovies(titleList.get(i), urlList.get(i)));
            }
            return categories;
        }

        @Override
        protected void onPostExecute(List<AllMovies> allMovies) {
            super.onPostExecute(allMovies);
            MainActivity.pbMovies.setVisibility(ProgressBar.INVISIBLE);
            MoviesAdapter.allMovies = allMovies;
            MainActivity.rvMovies.setAdapter(filmAdapter);
        }
    }
}
