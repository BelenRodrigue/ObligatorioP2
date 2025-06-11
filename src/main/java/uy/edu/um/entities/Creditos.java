package uy.edu.um.entities;

import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

public class Creditos {
    private int id;
    private MyLinkedListImpl<Actor> actores = new MyLinkedListImpl<>();
    private MyLinkedListImpl<Equipo> equipos = new MyLinkedListImpl<>();

    public Creditos(int id) {
        this.id = id;
    }

    public void addActor(Actor actor) {
        actores.add(actor);
    }

    public void addEquipo(Equipo equipo) {
        equipos.add(equipo);
    }
}



