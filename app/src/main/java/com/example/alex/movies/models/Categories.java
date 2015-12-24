package com.example.alex.movies.models;

public class Categories {
    public String title;
    public String data;
    public String urlImg;
    public String like;
    public String unlike;
    public String urlFilmInfo;

    public Categories(String title, String data, String urlImg, String like, String unlike, String urlFilmInfo) {
        this.title = title;
        this.data = data;
        this.urlImg = urlImg;
        this.like = like;
        this.unlike = unlike;
        this.urlFilmInfo = urlFilmInfo;
    }
}
