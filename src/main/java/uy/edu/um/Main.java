package uy.edu.um;

import uy.edu.um.entities.Coleccion;
import uy.edu.um.entities.Pelicula;
import uy.edu.um.entities.ItemHeap;
import uy.edu.um.tad.heap.MyHeapImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

public class Main {
    private MyLinkedListImpl<Pelicula> peliculas = new MyLinkedListImpl<>();
    private MyLinkedListImpl<Coleccion> colecciones = new MyLinkedListImpl<>();

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    private void top5PelConMasCalif() {
        MyHeapImpl<ItemHeap<Pelicula>> ingles = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Pelicula>> frances = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Pelicula>> italiano = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Pelicula>> espanol = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Pelicula>> portugues = new MyHeapImpl<>(5, true);

        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula peliIter = peliculas.get(i);
            Integer cantiCalif = peliIter.getCalificaciones().size();

            switch (peliIter.getIdioma()) {
                case "ingles":
                    addToHeap(ingles, new ItemHeap<>(peliIter, cantiCalif), 5);
                case "frances":
                    addToHeap(frances, new ItemHeap<>(peliIter, cantiCalif), 5);
                case "italiano":
                    addToHeap(italiano, new ItemHeap<>(peliIter, cantiCalif), 5);
                case "espanol":
                    addToHeap(espanol, new ItemHeap<>(peliIter, cantiCalif), 5);
                case "portugues":
                    addToHeap(portugues, new ItemHeap<>(peliIter, cantiCalif), 5);
            }
        }
    }

    private void top10PelConMejCalifMed() {
        MyHeapImpl<ItemHeap<Pelicula>> topTen = new MyHeapImpl<>(10, true);

        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula peliIter = peliculas.get(i);
            Integer calMed = peliIter.getCalifMedia();
            addToHeap(topTen, new ItemHeap<>(peliIter, calMed), 10);
        }
    }

    private <T> void addToHeap(MyHeapImpl<ItemHeap<T>> heap, ItemHeap<T> item, Integer tamanoHeap) {
        if (heap.size() < tamanoHeap) {
            heap.insert(item);
        } else {
            ItemHeap<T> min = heap.get();
            if (item.compareTo(min) > 0) {
                heap.delete();
                heap.insert(item);
            }
        }
    }

    private void top5ColeccConMasIngr() {
        MyHeapImpl<ItemHeap<Coleccion>> topFive = new MyHeapImpl<>(5, true);


        for (int i = 0; i < colecciones.size(); i++) {
            Coleccion coleccionActual = colecciones.get(i);
            MyLinkedListImpl<Pelicula> peliculasDeColecc = coleccionActual.getPeliculas();
            int sumatoriaGanancias = 0;

            for (int j = 0; j < peliculasDeColecc.size(); j++) {
                Pelicula peliculaActual = peliculasDeColecc.get(j);
                sumatoriaGanancias = sumatoriaGanancias + peliculaActual.getIngresoGenerado();

            }
            addToHeap(topFive, new ItemHeap<>(coleccionActual, sumatoriaGanancias), 5);
        }
    }

}

