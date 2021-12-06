package com.sudhanshu.controller;

import com.sudhanshu.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieController {

    private Map<String, Movie> movies = new HashMap<>();

    public Movie createMovie( String movieName, String movieType) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName, movieType);
        movies.put(movieId, movie);
        return movie;
    }

    public Movie getMovie(String movieId) {
        if (!movies.containsKey(movieId)) {
            throw new RuntimeException("Invalid Movie!");
        }
        return movies.get(movieId);
    }
}
