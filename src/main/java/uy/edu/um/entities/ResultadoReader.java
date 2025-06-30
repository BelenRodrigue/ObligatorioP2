package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Getter
@Setter
public class ResultadoReader {
    private MyHashImpl<Integer, Credits> credits;
    private MyHashImpl<Integer, Movie> movies;
    private MyLinkedListImpl<Rating> ratings;

    public ResultadoReader(MyHashImpl<Integer, Credits> credits, MyHashImpl<Integer, Movie> movies, MyLinkedListImpl<Rating> ratings) {
        this.credits = credits;
        this.movies = movies;
        this.ratings = ratings;
    }
}
