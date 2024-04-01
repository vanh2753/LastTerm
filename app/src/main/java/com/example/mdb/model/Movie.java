package com.example.mdb.model;

public class Movie {
    private String movieImage;
    private String movieName;
    private String movieTime;
    private String movieYear;
    private Float movieRate;
    private String[] movieActors;

    public Movie(String movieImage, String movieName, String movieTime, String movieYear, Float movieRate, String[] movieActors) {
        this.movieImage = movieImage;
        this.movieName = movieName;
        this.movieTime = movieTime;
        this.movieYear = movieYear;
        this.movieRate = movieRate;
        this.movieActors = movieActors;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public Float getMovieRate() {
        return movieRate;
    }

    public String[] getMovieActors() {
        return movieActors;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public void setMovieRate(Float movieRate) {
        this.movieRate = movieRate;
    }

    public void setMovieActors(String[] movieActors) {
        this.movieActors = movieActors;
    }
}
