package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Collection {
    private int id;
    private String name;
    private String posterPath;
    private String backdropPath;

    public Collection(String collectionString) {
        String[] result = collectionString.split(", 'backdrop_path': ");
        String backdropPath = result[1];

        result = result[0].split(", 'poster_path': ");
        String posterPath = result[1];

        result = result[0].split(", 'name': ");
        String name = result[1];

        result = result[0].split("'id': ");
        int id = Integer.parseInt(result[1]);

        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }
}
