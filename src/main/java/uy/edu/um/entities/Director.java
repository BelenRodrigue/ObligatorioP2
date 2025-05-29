package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Getter
@Setter
public class Director {
    private String nombre;
    private MyLinkedListImpl<Pelicula> peliculas;

}
