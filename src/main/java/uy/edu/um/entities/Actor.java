package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

@Getter
@Setter
public class Actor {
    private String nombre;
    private MyLinkedListImpl<Pelicula> peliculas;
    private MyLinkedListImpl<Double> calificaciones;


}
