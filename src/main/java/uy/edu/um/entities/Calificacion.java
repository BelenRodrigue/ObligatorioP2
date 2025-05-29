package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calificacion {
    private int puntaje;
    private Usuario calificador;
    private Pelicula puntuada;

}
