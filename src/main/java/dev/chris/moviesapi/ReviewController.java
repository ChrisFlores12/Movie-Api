package dev.chris.moviesapi;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //Create
    @PostMapping
    //A POST request is needed to create a new review
    //The POST endpoint accepts a JSON payload, which is why we use the Map<String, String> payload
    //This PostMapping endpoint is connected to the /api/v1/reviews endpoint

    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("body"), payload.get("imdbId")), HttpStatus.CREATED);
        //The payload is a map that contains the body of the review and the imdbId of the movie
        //Remember that CREATED returns a 201 status code
    }

    // Read
    // Gets all reviews for a specific movie
    @GetMapping("/{imdbId}")
    public ResponseEntity<List<Review>> getReviewsByImdbId(@PathVariable String imdbId) {
        return new ResponseEntity<List<Review>>(reviewService.getAllReviewsByImdbId(imdbId), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable ObjectId reviewId) {
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns a 204 status code
    }


}
