package uy.edu.um;

import  uy.edu.um.entities.Coleccion;
import uy.edu.um.entities.Pelicula;
import uy.edu.um.entities.ItemHeap;
import uy.edu.um.entities.ResultadoReader;
import uy.edu.um.tad.heap.MyHeapImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        ResultadoReader result = reader.readFiles();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Carga de datos");
            System.out.println("2. Ejecutar consultas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    long inicio = System.currentTimeMillis();
                    //CSVReader.readFiles();
                    long fin = System.currentTimeMillis();
                    System.out.println("Carga de datos exitosa en " + (fin - inicio) + " ms.");
                    break;
                case 2:
                    menuConsultas(sc);
                    break;
                case 3:
                    System.out.println("Programa Finalizado");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    static void menuConsultas(Scanner sc) {
        while (true) {
            System.out.println("--- Consultas ---");
            System.out.println("1. Top 5 de películas por idioma");
            System.out.println("2. Top 10 de películas con mejor calificación media");
            System.out.println("3. Top 5 de colecciones con mayores ingresos");
            System.out.println("4. Top 10 de directores por calificaciones");
            System.out.println("5. Actor con más calificaciones por mes");
            System.out.println("6. Usuarios con más calificaciones por género");
            System.out.println("7. Volver al Menú Principal");
            System.out.print("Seleccione una consulta: ");
            int opcion = sc.nextInt();
            long inicio, fin;
            switch (opcion) {
                case 1:
                    inicio = System.currentTimeMillis();
                    top5PelPorIdioma();
                    fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (fin - inicio) + " ms.");
                    break;
                case 2:
                    inicio = System.currentTimeMillis();
                    top10PelConMejCalifMed();
                    fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (fin - inicio) + " ms.");
                    break;
                case 3:
                    inicio = System.currentTimeMillis();
                    top5ColeccConMasIngr();
                    fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (fin - inicio) + " ms.");
                    break;
                case 4:
                    inicio = System.currentTimeMillis();
                    top10DirecMejorCal();
                    fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (fin - inicio) + " ms.");
                    break;
                case 5:
                    inicio = System.currentTimeMillis();
                    ActorMasCalificadoMensual();
                    fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (fin - inicio) + " ms.");
                    break;
                case 6:
                    inicio = System.currentTimeMillis();
                    UsuariosConMasCalificPorGenero();
                    fin = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (fin - inicio) + " ms.");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

            private void top5PelConMasCalif () {
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

            private void top10PelConMejCalifMed () {
                MyHeapImpl<ItemHeap<Pelicula>> topTen = new MyHeapImpl<>(10, true);

                for (int i = 0; i < peliculas.size(); i++) {
                    Pelicula peliIter = peliculas.get(i);
                    Integer calMed = peliIter.getCalifMedia();
                    addToHeap(topTen, new ItemHeap<>(peliIter, calMed), 10);
                }
            }

            private <T > void addToHeap (MyHeapImpl < ItemHeap < T >> heap, ItemHeap < T > item, Integer tamanoHeap){
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

            private void top5ColeccConMasIngr () {
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
            private void top10DirecMejorCal () {
            }
            private void ActorMasCalificadoMensual () {
            }
            private void UsuariosConMasCalificPorGenero () {
            }
}