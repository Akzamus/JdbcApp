package com.akzam.JdbcApp.movie;

import com.akzam.JdbcApp.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieDAO movieDAO;

    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }


    public List<Movie> getMovies() {
        return movieDAO.selectMovies();
    }

    public void addNewMovie(Movie movie) {
        int result = movieDAO.insertMovie(movie);
        if(result !=1)
            throw new IllegalStateException("oops something went wrong");
    }

    public void deleteMovie(Integer id) {
        Optional<Movie> movies = movieDAO.selectMovieById(id);
        movies.ifPresentOrElse(
                movie -> {
                    int result = movieDAO.deleteMovie(id);
                    if (result != 1)
                        throw new IllegalStateException("oops could not delete movie");
                },
                () -> {
                    throw new NotFoundException(String.format("Movie with id %s not found", id));
                });
    }

    public Movie getMovie(Integer id) {
        return movieDAO.selectMovieById(id)
                       .orElseThrow(() -> new NotFoundException(String.format("Movie with id %s not found", id)));
    }
}
