package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalifDirector {
    private String nombre;
    private int cantidadPelis;
    private int cantiCalif;
    private Double calif;

    public CalifDirector(String nombre) {
        this.nombre = nombre;
        this.cantidadPelis = 1;
        this.cantiCalif = 0;
        this.calif = 0.0;
    }

    public void agregarPelicula(int cantiCalif, Double calif) {
        this.cantidadPelis += 1;
        this.cantiCalif += cantiCalif;
        this.calif += calif;
    }

    public Double getMedia() {
        return this.calif / this.cantiCalif;
    }
}
