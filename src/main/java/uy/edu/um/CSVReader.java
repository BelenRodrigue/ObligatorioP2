package uy.edu.um;

import uy.edu.um.entities.*;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
    private MyHashImpl<Integer, Credits> credits = new MyHashImpl<>();
    private MyHashImpl<Integer, Movie> movies = new MyHashImpl<>();
    private MyLinkedListImpl<Rating> ratings = new MyLinkedListImpl<>();

    public ResultadoReader readFiles() {
        try {
            readCredits();
            readMovies();
            readRatings();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        return new ResultadoReader(credits, movies, ratings);
    }

    private BufferedReader read(String path) throws IOException {
        File csvFile = new File(path);
        FileReader fileReader = new FileReader(csvFile);
        return new BufferedReader(fileReader);
    }

    private void readRatings() throws IOException {
        BufferedReader buffer = read("ratings_1mm.csv");
        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            String[] result = line.split(",");

            int userId = Integer.parseInt(result[0]);
            int movieId = Integer.parseInt(result[1]);
            double rating = Double.parseDouble(result[2]);
            long timestamp = Long.parseLong(result[3]);

            ratings.add(new Rating(userId, movieId, rating, timestamp));
        }
    }

    private void readMovies() throws IOException {
        BufferedReader buffer = read("movies_metadata.csv");
        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            if (line.startsWith("TRUE") || line.startsWith("FALSE")) {
                String[] result = line.split(",", 2);
                boolean adults = Objects.equals(result[0], "TRUE");

                String belongsCollectionString = "";
                if (result[1].startsWith(",")) {
                    result = result[1].split(",", 2);
                } else {
                    result = result[1].split("}\",", 2);
                    belongsCollectionString = result[0] + "}";
                }

                result = result[1].split(",", 2);
                int budget = Integer.parseInt(result[0]);

                result = result[1].split("]\",|],", 2);
                String genresString = result[0];

                String homepage = "";
                if (result[1].startsWith(",")) {
                    result = result[1].split(",", 2);
                } else {
                    if (result[1].startsWith("\"")) {
                        result = result[1].split("\",", 2);
                    } else {
                        result = result[1].split(",", 2);
                    }
                    homepage = result[0];
                }

                result = result[1].split(",", 2);
                int id = Integer.parseInt(result[0]);

                result = result[1].split(",", 2);
                String imdbId = result[0];

                result = result[1].split(",", 2);
                String originalLanguage = result[0];

                if (result[1].startsWith("\"")) {
                    result = result[1].split("\",", 2);
                } else {
                    result = result[1].split(",", 2);
                }
                String originalTitle = result[0];

                String overview = "";
                if (result[1].startsWith(",")) {
                    result = result[1].split(",", 2);
                } else {
                    if (result[1].startsWith("\"")) {
                        result = result[1].split("\",", 2);
                    } else {
                        result = result[1].split(",", 2);
                    }
                    overview = result[0];
                }

                if (result.length == 2 && !result[1].isEmpty()) {
                    String productionCompanies = "";
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split("]\",|],", 2);
                        productionCompanies = result[0];
                    }

                    String productionCountries = "";
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split("]\",|],", 2);
                        productionCountries = result[0];
                    }

                    String releasedDate = "";
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split(",", 2);
                        releasedDate = result[0];
                    }

                    long revenue = 0;
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split(",", 2);
                        revenue = Long.parseLong(result[0]);
                    }

                    double runtime = 0.0;
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split(",", 2);
                        runtime = Double.parseDouble(result[0]);
                    }

                    String spokenLanguages = "";
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split("]\",|],", 2);
                        spokenLanguages = result[0];
                    }

                    String status = "";
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split(",", 2);
                        status = result[0];
                    }

                    String tagline = "";
                    if (result[1].startsWith(",")) {
                        result = result[1].split(",", 2);
                    } else {
                        result = result[1].split(",", 2);
                        tagline = result[0];
                    }

                    String title = result[1];

                    // Defino un pattern para separar los objetos dentro de la lista
                    Pattern pattern = Pattern.compile("\\{([^}]*)}");

                    MyLinkedListImpl<Collection> collections = new MyLinkedListImpl<>();
                    MyLinkedListImpl<Genero> generes = new MyLinkedListImpl<>();

                    // Itero entre la lista de collections
                    Matcher matcher = pattern.matcher(belongsCollectionString);
                    while (matcher.find()) {
                        collections.add(new Collection(matcher.group(1)));
                    }

                    // Itero entre la lista de generos
                    matcher = pattern.matcher(genresString);
                    while (matcher.find()) {
                        generes.add(new Genero(matcher.group(1)));
                    }

                    movies.put(id, new Movie(
                            adults,
                            collections,
                            budget,
                            generes,
                            homepage,
                            id,
                            imdbId,
                            originalLanguage,
                            originalTitle,
                            overview,
                            productionCompanies,
                            productionCountries,
                            releasedDate,
                            revenue,
                            runtime,
                            spokenLanguages,
                            status,
                            tagline,
                            title
                    ));
                }
            }
        }
    }

    private void readCredits() throws IOException {
        BufferedReader buffer = read("credits.csv");
        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            // Separamos por el caracter "]"
            // lo que nos deja:
            // indice 0: lista de cast
            // indice 1: lista de crew
            // indice 2: el id
            String[] data = line.split("]\",|],");

            // Obtengo cast
            String castString = data[0];
            // Obtengo crew
            String crewString = data[1];
            // Obtengo el id eliminando la coma
            String idString = data[2].replace(",", "");
            int idInteger = Integer.parseInt(idString);

            // Defino un pattern para separar los objetos dentro de la lista
            Pattern pattern = Pattern.compile("\\{([^}]*)}");

            MyLinkedListImpl<CastMember> castMembers = new MyLinkedListImpl<>();
            MyLinkedListImpl<CrewMember> crewMembers = new MyLinkedListImpl<>();

            // Itero entre la lista de cast
            Matcher matcher = pattern.matcher(castString);
            while (matcher.find()) {
                castMembers.add(new CastMember(matcher.group(1)));
            }

            // Itero entre la lista de crew
            matcher = pattern.matcher(crewString);
            while (matcher.find()) {
                crewMembers.add(new CrewMember(matcher.group(1)));
            }

            credits.put(idInteger, new Credits(idInteger, castMembers, crewMembers));
        }
    }
}



