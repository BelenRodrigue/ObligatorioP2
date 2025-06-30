package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promedio {
    public Integer cantidadCalif;
    public Double califi;

    public Promedio(Double califi) {
        this.cantidadCalif = 1;
        this.califi = califi;
    }

    public void sumarCalif(Double califiNueva) {
        this.cantidadCalif += 1;
        this.califi += califiNueva;
    }

    public Double calcular() {
        return this.califi / this.cantidadCalif;
    }
}
