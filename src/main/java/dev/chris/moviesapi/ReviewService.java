package dev.chris.moviesapi;



import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService {

    //Need our repository for the CRUD operations
    private final ReviewRepository reviewRepository; // useful but less flexible than MongoTemplate

    //Need our movie repository to get the movie object
    private final MongoTemplate mongoTemplate; // we can use this to make a new dynamic query or, in other words, requests for info

    private final MovieRepository movieRepository; //Use this grab movie objects from the database


    //Constructor injection for review repository and MongoTemplate
    //This is the preferred way to inject multiple dependencies because multiple constructors can confuse the Spring framework
    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
        this.movieRepository = movieRepository;
    }

    //Create
    public Review createReview(String body, String imdbId){
        Review review = reviewRepository.insert(new Review(body));  //Creates a new review object and inserts it into the database

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))  //This line finds the movie with the given imdbId / the one the user is trying to review
                .apply(new Update().push("reviewIds").value(review))  //This line pushes a new review into the movie's reviewIds array and updates it
                .first();   //This line updates the first movie that matches the criteria
        return review;
    }

    // What if users want to see all the reviews for a specific movie?
    // We can create a method that will search for a list of reviews based on the reviewIds in the Movie class

    public List<Review> getAllReviewsByImdbId(String imdbId) {

        // This finds the movie by imdbId and throws error exceptions if needed
        Movie movie = movieRepository.findMovieByImdbId(imdbId).orElseThrow(
                () -> new RuntimeException("Movie with " + imdbId + " not found")
        );

        return movie.getReviewIds();
    }








}
