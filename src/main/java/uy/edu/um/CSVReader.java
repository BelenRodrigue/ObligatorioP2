package uy.edu.um;

import uy.edu.um.entities.ResultadoReader;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class CSVReader {

    public ResultadoReader readFiles() {
        ResultadoReader result = new ResultadoReader();

        try {

        } catch (Exception e) {

        }

        return result;
    }

    private BufferedReader read(String path) throws IOException {
        File csvFile = new File(path);
        FileReader fileReader = new FileReader(csvFile);
        return new BufferedReader(fileReader);
    }

    private void readMovies() {

    }

    private void readCredits() throws IOException {
        BufferedReader buffer = read("credits.csv");
        String line = buffer.readLine();

        while ((line = buffer.readLine()) != null) {
            String[] fields = line.split(",");

            // Leer cast
            //for (int i=0; i<fields[0])

            // Leer crew


            // Leer id



            for (String field : fields) {
                row.add(field.trim());
            }
            data.add(row);
        }
    }

    private void readRatings() {

    }
}



