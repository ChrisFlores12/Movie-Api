package dev.chris.moviesapi.reviews;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class Review {
    @Id
    private ObjectId id;
    private String body;


    // No args constructor
    public Review() {
    }

    // All arg constructor
    public Review(ObjectId id, String body) {
        this.id = id;
        this.body = body;
    }

    //Only body constructor
    //This allows us to create a review without an object id
    public Review(String body) {
        this.body = body;
    }

    //POJO getters and setters

    public Object getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
