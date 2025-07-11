package dev.chris.moviesapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    //A POST request is needed to create a new review
    //The POST endpoint accepts a JSON payload, which is why we use the Map<String, String> payload
    //This PostMapping endpoint is connected to the /api/v1/reviews endpoint

    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("body"), payload.get("imdbId")), HttpStatus.CREATED);
        //The payload is a map that contains the body of the review and the imdbId of the movie
        //Remember that CREATED returns a 201 status code
    }
}
