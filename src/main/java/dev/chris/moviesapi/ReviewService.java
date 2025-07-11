package dev.chris.moviesapi;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    //Need our repository for the CRUD operations
    private final ReviewRepository reviewRepository;

    //Need our movie repository to get the movie object
    private final MongoTemplate mongoTemplate; // we can use this to make a new dynamic query or, in other words, requests for info


    //Constructor injection for review repository and MongoTemplate
    //This is the preferred way to inject multiple dependencies because multiple constructors can confuse the Spring framework
    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
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






}
