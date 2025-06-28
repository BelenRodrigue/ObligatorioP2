package uy.edu.um.entities;

import uy.edu.um.tad.hash.MyHashImpl;

public class ResultadoReader {
    private MyHashImpl<Integer, Credits> credits;
    private MyHashImpl<Integer, Movie> movies;
    private MyHashImpl<Integer, Rating> ratings;

    public ResultadoReader(MyHashImpl<Integer, Credits> credits, MyHashImpl<Integer, Movie> movies, MyHashImpl<Integer, Rating> ratings) {
        this.credits = credits;
        this.movies = movies;
        this.ratings = ratings;
    }
}
