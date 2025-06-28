package uy.edu.um.entities;

public class CastMember {
    int castId;
    String character;
    String creditId;
    int gender;
    int id;
    String name;
    int order;
    String profilePath;

    public CastMember(String castString) {
        String[] result = castString.split(", 'profile_path': ");
        String profilePath = result[1];

        result = result[0].split(", 'order': ");
        int order = Integer.parseInt(result[1]);

        result = result[0].split(", 'name': ");
        String name = result[1];

        result = result[0].split(", 'id': ");
        int id = Integer.parseInt(result[1]);

        result = result[0].split(", 'gender': ");
        int gender = Integer.parseInt(result[1]);

        result = result[0].split(", 'credit_id': ");
        String creditId = result[1];

        result = result[0].split(", 'character': ");
        String character = result[1];

        result = result[0].split("'cast_id': ");

        this.castId = Integer.parseInt(result[1]);
        this.character = character;
        this.creditId = creditId;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profilePath = profilePath;
    }

}