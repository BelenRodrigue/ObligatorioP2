package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

@Getter
@Setter
public class Coleccion {
    private int id;
    private String titulo;
    private MyLinkedListImpl<Pelicula> peliculas;


}
