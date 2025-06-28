package uy.edu.um.entities;

public class Genero {
    private int id;
    private String name;


    public Genero(String generoString) {
        String[] result = generoString.split(", 'name': ");
        String name = result[1];

        result = result[0].split("'id': ");
        int id = Integer.parseInt(result[1]);

        this.id = id;
        this.name = name;
    }
}
