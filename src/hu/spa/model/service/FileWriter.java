package hu.spa.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {

    private final String filename;

    public FileWriter(String filename) {
        this.filename = filename;
    }

    public void write(List<String> lines){

        try {
            Files.write(Paths.get(filename), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
