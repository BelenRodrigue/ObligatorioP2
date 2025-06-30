package uy.edu.um;

import uy.edu.um.entities.*;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.heap.MyHeapImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static MyHashImpl<Integer, Credits> credits;
    private static MyHashImpl<Integer, Movie> movies;
    private static MyLinkedListImpl<Rating> ratings;

    public static void main(String[] args) {
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
                    CSVReader reader = new CSVReader();
                    ResultadoReader result = reader.readFiles();

                    credits = result.getCredits();
                    movies = result.getMovies();
                    ratings = result.getRatings();
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
                    top5PelConMasCalif();
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

    private static void top5PelConMasCalif() {
        MyHeapImpl<ItemHeap<Movie>> ingles = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Movie>> frances = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Movie>> italiano = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Movie>> espanol = new MyHeapImpl<>(5, true);
        MyHeapImpl<ItemHeap<Movie>> portugues = new MyHeapImpl<>(5, true);

        MyHashImpl<Integer, Integer> cantRatings = new MyHashImpl<>();

        System.out.println("empieza a iterar los ratings");

        for (int ratingIndex = 0; ratingIndex < ratings.size(); ratingIndex++) {
            Rating ratingIter = ratings.get(ratingIndex);

            if (cantRatings.contains(ratingIter.getMovieId())) {
                Integer nuevaCantidadDeRatings = cantRatings.get(ratingIter.getMovieId()) + 1;
                cantRatings.put(ratingIter.getMovieId(), nuevaCantidadDeRatings);
            } else {
                cantRatings.put(ratingIter.getMovieId(), 1);
            }
        }

        System.out.println("termino de iterar los ratings");

        MyList<Integer> cantRatingsMovieId = cantRatings.keys();
        MyList<Integer> cantRatingsCount = cantRatings.values();

        for (int cantRatingIndex = 0; cantRatingIndex < cantRatingsMovieId.size(); cantRatingIndex++) {
            Integer movieIdIter = cantRatingsMovieId.get(cantRatingIndex);
            Integer cantRatMovie = cantRatingsCount.get(cantRatingIndex);
            Movie movieIter = movies.get(movieIdIter);

            switch (movieIter.getOriginalLanguage()) {
                case "en":
                    System.out.println("agrego al heap ingles");
                    addToHeap(ingles, new ItemHeap<>(movieIter, cantRatMovie), 5);
                case "fr":
                    addToHeap(frances, new ItemHeap<>(movieIter, cantRatMovie), 5);
                case "it":
                    addToHeap(italiano, new ItemHeap<>(movieIter, cantRatMovie), 5);
                case "es":
                    addToHeap(espanol, new ItemHeap<>(movieIter, cantRatMovie), 5);
                case "pt":
                    addToHeap(portugues, new ItemHeap<>(movieIter, cantRatMovie), 5);
            }
        }

        System.out.println("termino de agregar a los heap");

        imprimirHeap(ingles);
        imprimirHeap(frances);
        imprimirHeap(italiano);
        imprimirHeap(espanol);
        imprimirHeap(portugues);
    }

    private static void imprimirHeap(MyHeapImpl<ItemHeap<Movie>> heap) {
        while (heap.size() > 0) {
            ItemHeap<Movie> movieItem = heap.get();
            Movie movie = movieItem.getItem();
            Integer calif = movieItem.getComparacion();
            heap.delete();

            System.out.println(movie.getId() + ", ");
            System.out.println(movie.getTitle() + ", ");
            System.out.println(calif + ", ");
            System.out.println(movie.getOriginalLanguage());
        }
    }

    private static void top10PelConMejCalifMed() {
        MyHeapImpl<ItemHeapDouble<Movie>> top = new MyHeapImpl<>(10, true);
        MyHashImpl<Integer, Promedio> cantRatings = new MyHashImpl<>();

        for (int ratingIndex = 0; ratingIndex < ratings.size(); ratingIndex++) {
            Rating ratingIter = ratings.get(ratingIndex);

            if (cantRatings.contains(ratingIter.getMovieId())) {
                Promedio nuevoPromedio = cantRatings.get(ratingIter.getMovieId());
                nuevoPromedio.sumarCalif(ratingIter.getRating());
                cantRatings.put(ratingIter.getMovieId(), nuevoPromedio);
            } else {
                Promedio promedio = new Promedio(ratingIter.getRating());
                cantRatings.put(ratingIter.getMovieId(), promedio);
            }
        }

        MyList<Integer> moviesIds = cantRatings.keys();
        MyList<Promedio> promedios = cantRatings.values();

        for (int cantRatingIndex = 0; cantRatingIndex < moviesIds.size(); cantRatingIndex++) {
            Integer movieIdIter = moviesIds.get(cantRatingIndex);
            Promedio promedio = promedios.get(cantRatingIndex);
            Movie movieIter = movies.get(movieIdIter);

            addToHeapDouble(top, new ItemHeapDouble<>(movieIter, promedio.calcular()), 10);
        }

        while (top.size() > 0) {
            ItemHeapDouble<Movie> movieItem = top.get();
            Movie movie = movieItem.getItem();
            Double calif = movieItem.getComparacion();
            top.delete();
            System.out.println(movie.getId() + ", ");
            System.out.println(movie.getTitle() + ", ");
            System.out.println(calif);
        }
    }

    private static <T> void addToHeap(MyHeapImpl<ItemHeap<T>> heap, ItemHeap<T> item, Integer tamanoHeap) {
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

    private static <T> void addToHeapDouble(MyHeapImpl<ItemHeapDouble<T>> heap, ItemHeapDouble<T> item, Integer tamanoHeap) {
        if (heap.size() < tamanoHeap) {
            heap.insert(item);
        } else {
            ItemHeapDouble<T> min = heap.get();
            if (item.compareTo(min) > 0) {
                heap.delete();
                heap.insert(item);
            }
        }
    }

    private static <T> void addToHeapLong(MyHeapImpl<ItemHeapLong<T>> heap, ItemHeapLong<T> item, Integer tamanoHeap) {
        if (heap.size() < tamanoHeap) {
            heap.insert(item);
        } else {
            ItemHeapLong<T> min = heap.get();
            if (item.compareTo(min) > 0) {
                heap.delete();
                heap.insert(item);
            }
        }
    }

    private static void top5ColeccConMasIngr() {
        MyHeapImpl<ItemHeapLong<IngresoCollection>> top = new MyHeapImpl<>(5, true);
        MyHashImpl<Integer, IngresoCollection> ingresos = new MyHashImpl<>();

        for (int i =0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            MyLinkedListImpl<Collection> collections = new MyLinkedListImpl<>();

            for (int j=0; j<collections.size(); j++) {
                Collection collection = collections.get(j);

                if (ingresos.contains(collection.getId())) {
                    IngresoCollection ingColl = ingresos.get(collection.getId());
                    ingColl.sumMovie(movie.getId(), movie.getRevenue());
                    ingresos.put(collection.getId(), ingColl);
                } else {
                    IngresoCollection ingColl = new IngresoCollection(collection.getId(), collection.getName(), movie.getId(), movie.getRevenue());
                    ingresos.put(collection.getId(), ingColl);
                }
            }
        }

        MyList<Integer> collIds = ingresos.keys();
        MyList<IngresoCollection> ingColl = ingresos.values();

        for (int collsInd = 0; collsInd < collIds.size(); collsInd++) {
            IngresoCollection collIter = ingColl.get(collsInd);
            addToHeapLong(top, new ItemHeapLong<>(collIter, collIter.getIngreso()), 5);
        }

        while (top.size() > 0) {
            ItemHeapLong<IngresoCollection> ingresoItem = top.get();
            IngresoCollection ingrColl = ingresoItem.getItem();
            Long ingr = ingresoItem.getComparacion();
            top.delete();
            System.out.println(ingrColl.getId() + ", ");
            System.out.println(ingrColl.getTitulo() + ", ");
            System.out.println(ingrColl.getCantidadPelis() + ", ");
            for(int p=0; p<ingrColl.getPelisId().size(); p++){
                System.out.println(ingrColl.getPelisId().get(p) + ", ");
            }
            System.out.println(ingr);
        }
    }

    private static void top10DirecMejorCal() {
        MyHashImpl<Integer, Promedio> cantRatings = new MyHashImpl<>();
        MyHeapImpl<ItemHeapDouble<CalifDirector>> top = new MyHeapImpl<>(10, true);

        for (int ratingIndex = 0; ratingIndex < ratings.size(); ratingIndex++) {
            Rating ratingIter = ratings.get(ratingIndex);

            if (cantRatings.contains(ratingIter.getMovieId())) {
                Promedio nuevoPromedio = cantRatings.get(ratingIter.getMovieId());
                nuevoPromedio.sumarCalif(ratingIter.getRating());
                cantRatings.put(ratingIter.getMovieId(), nuevoPromedio);
            } else {
                Promedio promedio = new Promedio(ratingIter.getRating());
                cantRatings.put(ratingIter.getMovieId(), promedio);
            }
        }

        MyHashImpl<Integer, CalifDirector> califDir = new MyHashImpl<>();

        for (int i=0; i<credits.size(); i++) {
            Credits credit = credits.get(i);
            MyLinkedListImpl<CrewMember> crewMembers = credit.getCrewMembers();
            for (int j=0; j<crewMembers.size(); j++) {
                CrewMember member = crewMembers.get(j);
                if (Objects.equals(member.getJob(), "Director")) {
                    if (califDir.contains(member.getId())) {
                        CalifDirector cali = califDir.get(member.getId());
                        Promedio promedio = cantRatings.get(credit.getId());
                        cali.agregarPelicula(promedio.cantidadCalif, promedio.califi);
                        califDir.put(member.getId(), cali);
                    } else {
                        CalifDirector cali = new CalifDirector(member.getName());
                        Promedio promedio = cantRatings.get(credit.getId());
                        cali.agregarPelicula(promedio.cantidadCalif, promedio.califi);
                        califDir.put(member.getId(), cali);
                    }
                }
            }
        }

        MyList<Integer> dirIds = califDir.keys();
        MyList<CalifDirector> califs = califDir.values();

        for (int caliInd = 0; caliInd < dirIds.size(); caliInd++) {
            CalifDirector calDir = califs.get(caliInd);
            if (calDir.getCantiCalif() > 100 && calDir.getCantidadPelis() > 1) {
                addToHeapDouble(top, new ItemHeapDouble<>(calDir, calDir.getMedia()), 10);
            }
        }

        while (top.size() > 0) {
            ItemHeapDouble<CalifDirector> calItem = top.get();
            CalifDirector calDir = calItem.getItem();
            Double media = calItem.getComparacion();
            top.delete();
            System.out.println(calDir.getNombre() + ", ");
            System.out.println(calDir.getCantidadPelis() + ", ");
            System.out.println(media);
        }
    }

    private void ActorMasCalificadoMensual() {
    }

    private void UsuariosConMasCalificPorGenero() {
    }
}