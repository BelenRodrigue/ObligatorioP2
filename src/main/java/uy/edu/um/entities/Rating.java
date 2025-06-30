package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    private int userId;
    private int movieId;
    private double rating;
    private long timestamp;

    public Rating(int userId, int movieId, double rating, long timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
    }
}
