package com.example.alex.movies.models;

public class Movie {
    public String titleRu;
    public String titleEn;
    public String txtFilm;
    public String urlImg;

    public Movie(String titleRu, String titleEn, String txtFilm, String urlImg) {
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.txtFilm = txtFilm;
        this.urlImg = urlImg;
    }
}
