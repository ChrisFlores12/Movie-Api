package dev.chris.moviesapi.movies;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    //This class is a service layer that interacts with the MovieRepository to perform CRUD operations on Movie objects.

    private final MovieRepository movieRepository;

    // Constructor injection
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> movieById(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);

    }



}
