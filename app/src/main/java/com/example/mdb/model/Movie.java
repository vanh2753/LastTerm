package com.example.mdb.model;

import java.util.ArrayList;

public class Movie {
    private String movieId;
    private String movieImage;
    private String movieName;
    private String movieTime;
    private String movieYear;
    private String movieGenre;
    private Float movieRate;
    private String movieActors;

    public Movie(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Movie(){
        // constructor
    }
    public Movie(String movieImage, String movieName, String movieTime, String movieYear, String movieGenre, String movieActors, Float movieRate) {
        this.movieImage = movieImage;
        this.movieName = movieName;
        this.movieTime = movieTime;
        this.movieYear = movieYear;
        this.movieGenre = movieGenre;
        this.movieActors = movieActors;
        this.movieRate = movieRate;
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

    public String getMovieGenre() {
        return movieGenre;
    }

    public Float getMovieRate() {
        return movieRate;
    }

    public String getMovieActors() {
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

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public void setMovieRate(Float movieRate) {
        this.movieRate = movieRate;
    }

    public void setMovieActors(String movieActors) {
        this.movieActors = movieActors;
    }
}
