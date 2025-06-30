package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Getter
@Setter
public class IngresoCollection {
    private int id;
    private String titulo;
    private int cantidadPelis;
    private MyLinkedListImpl<Integer> pelisId;
    private Long ingreso;

    public IngresoCollection(int id, String titulo, int peliId, Long ingreso) {
        this.id = id;
        this.titulo = titulo;
        this.cantidadPelis = 1;
        this.pelisId.add(peliId);
        this.ingreso = ingreso;
    }

    public void sumMovie(int peliId, Long ingreso) {
        this.cantidadPelis += 1;
        this.pelisId.add(peliId);
        this.ingreso += ingreso;
    }
}
