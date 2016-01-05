package com.example.alex.movies.models;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String URL_DATE_OF_RENOVATION = "http://fs.to/video/films/?sort=new";
    public static final String URL_BY_RATING = "http://fs.to/video/films/?sort=rating";
    public static final String URL_BY_YEAR_OF_PRODUCTION = "http://fs.to/video/films/?sort=year";
    public static final String URL_POPULAR_CATEGORIES = "http://fs.to/video/films/?sort=popularity";
    public static final String URL_IN_TREND = "http://fs.to/video/films/?sort=trend";

    public static final String TABLE_MOVIE = "movie_db";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_LIKE = "like";
    public static final String FIELD_UNLIKE = "unlike";
    public static final String FIELD_POSTER_URL = "poster";
    public static final String FIELD_URL_MOVIES_INFO = "info";
    public static final String FIELD_DATA = "data";

    public static List<Categories> CATEGORIES = new ArrayList<>();

    public static final int PARSED_CONSTANT_IMG_MOVIES = 1;

    public static final int START_PARSED_URL_FILM = 56;
    public static final int END_PARSED_URL_FILM = 74;
}
