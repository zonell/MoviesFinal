package com.example.alex.movies.models;

public class Movie {
    public String titleRu;
    public String like;
    public String unlike;
    public String txtFilm;
    public String urlImg;
    public String data;
    public String urlMovies;

    public Movie(String titleRu, String like, String unlike, String txtFilm, String urlImg, String data, String urlMovies) {
        this.titleRu = titleRu;
        this.like = like;
        this.unlike = unlike;
        this.txtFilm = txtFilm;
        this.urlImg = urlImg;
        this.data = data;
        this.urlMovies = urlMovies;
    }
}
