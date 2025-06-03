package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Getter
@Setter
public class Pelicula {
    private Integer id;
    private String titulo;
    private String idioma;
    private Integer ingresoGenerado;
    private MyLinkedListImpl<String> generos;
    private MyLinkedListImpl<Calificacion> calificaciones;

    public Integer getCalifMedia() {
        int total = 0;

        for (int i = 0; i < calificaciones.size(); i++) {
            Calificacion calIter = calificaciones.get(i);
            total += calIter.getPuntaje();
        }

        return total / calificaciones.size();
    }
}

