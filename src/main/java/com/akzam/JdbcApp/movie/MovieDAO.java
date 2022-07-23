package com.akzam.JdbcApp.movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    List<Movie> selectMovies();
    int insertMovie(Movie movie);
    int deleteMovie(int id);
    Optional<Movie> selectMovieById(int id);
}
