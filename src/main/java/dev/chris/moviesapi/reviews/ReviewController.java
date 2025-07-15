package dev.chris.moviesapi.reviews;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //Create

    //A POST request is needed to create a new review
    //The POST endpoint accepts a JSON payload, which is why we use the Map<String, String> payload
    //This PostMapping endpoint is connected to the /api/v1/reviews endpoint

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
        //The payload is a map that contains the body of the review and the imdbId of the movie
        //Remember that CREATED returns a 201 status code
    }

    // Read
    // This function is already implemented in the MovieController
    // Remember that we can get all reviews for a specific movie by calling the getSingleMovieById method in the MovieController

    // Update
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable ObjectId reviewId, @RequestBody Map<String, String> payload) {
        Review updatedReview = reviewService.updateReviewById(reviewId, payload.get("reviewBody"));
        return new ResponseEntity<>(updatedReview, HttpStatus.OK); // Returns a 200 status code with the updated review
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable ObjectId reviewId) {
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns a 204 status code
    }


}
