package com.example.alex.movies.parsed;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.adapter.MoviesAdapter;
import com.example.alex.movies.models.Categories;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsedMoviesCategories {
    private static Elements titleTxt;
    private static Elements dataTxt;
    private static Elements like;
    private static Elements unlike;

    private static ArrayList<String> titleList = new ArrayList<>();
    private static ArrayList<String> dataList = new ArrayList<>();
    private static ArrayList<String> urlList = new ArrayList<>();
    private static ArrayList<String> urlInfoFilmsList = new ArrayList<>();
    private static ArrayList<String> likeList = new ArrayList<>();
    private static ArrayList<String> unlikeList = new ArrayList<>();

    public static MoviesAdapter moviesAdapter = new MoviesAdapter(MainActivity.context);

    public static void initializeData(String url) {
        new NewTreadParsed(url).execute();
    }

    public static class NewTreadParsed extends AsyncTask<List<Categories>, Void, List<Categories>> {
        private String url;

        public NewTreadParsed(String url) {
            this.url = url;
        }

        @Override
        protected List<Categories> doInBackground(List<Categories>... params) {
            Document docTxt;
            List<Categories> categories = new ArrayList<>();
            try {
                docTxt = Jsoup.connect(url).get();
                titleTxt = docTxt.select(".b-poster-tile__title-full");
                dataTxt = docTxt.select(".b-poster-tile__title-info-items");
                like = docTxt.select(".b-poster-tile__title-info-vote-positive");
                unlike = docTxt.select(".b-poster-tile__title-info-vote-negative");
                titleList.clear();
                dataList.clear();
                urlList.clear();
                urlInfoFilmsList.clear();
                likeList.clear();
                unlikeList.clear();

                for (Element contents : titleTxt) {
                    titleList.add(contents.text());
                }
                for (Element contents : dataTxt) {
                    dataList.add(contents.text());
                }
                for (int i = 0; i < titleList.size(); i++) {
                    urlList.add("http:" + docTxt.select("span.b-poster-tile__image > img[src]").get(i).attr("src").toString());
                }
                for (int i = 0; i < titleList.size(); i++) {
                    urlInfoFilmsList.add("http://fs.to" + docTxt.select("div.b-poster-tile    > a[href]").get(i).attr("href").toString());
                }
                for (Element contents : like) {
                    likeList.add(contents.text());
                }
                for (Element contents : unlike) {
                    unlikeList.add(contents.text());
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            for (int i = 0; i < titleList.size(); i++) {
                categories.add(new Categories(titleList.get(i), dataList.get(i), urlList.get(i), likeList.get(i), unlikeList.get(i), urlInfoFilmsList.get(i)));
            }
            return categories;
        }

        @Override
        protected void onPostExecute(List<Categories> categories) {
            super.onPostExecute(categories);
            MainActivity.pbMovies.setVisibility(ProgressBar.INVISIBLE);
            MoviesAdapter.categories = categories;
            MainActivity.rvMovies.setAdapter(moviesAdapter);
        }
    }
}
