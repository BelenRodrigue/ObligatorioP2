package uy.edu.um.entities;


import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Getter
@Setter

public class Pelicula {
    private int id;
    private String titulo;
    private String idioma;
    private int ingresoGenerado;
    private MyLinkedListImpl<String> generos;





}
