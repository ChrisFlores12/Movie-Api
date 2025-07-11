package dev.chris.moviesapi;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    //We have to implement our own methods that are not provided by MongoRepository

    Optional<Movie> findMovieByImdbId(String imdbId);//Spring will automatically implement this method for us
}
