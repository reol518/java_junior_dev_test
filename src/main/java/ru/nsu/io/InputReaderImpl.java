package ru.nsu.io;

import com.google.gson.Gson;
import ru.nsu.criterias.SearchCriterias;
import ru.nsu.criterias.StatCriteria;

import java.io.FileReader;
import java.io.IOException;

public class InputReaderImpl implements InputReader {
    private Gson gson;

    public InputReaderImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public SearchCriterias readSearchCriterias(String input) throws IOException {
        String fileAsString = getFileAsString(input);
        return gson.fromJson(fileAsString, SearchCriterias.class);
    }

    @Override
    public StatCriteria readStatCriteria(String input) throws IOException {
        String fileAsString = getFileAsString(input);
        return gson.fromJson(fileAsString, StatCriteria.class);
    }

    private String getFileAsString(String input) throws IOException {
        String fileAsString = "";
        try (FileReader reader = new FileReader(input)) {
            StringBuilder stringBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            fileAsString = stringBuilder.toString();
        } catch (IOException e) {
            throw new IOException();
        }
        return fileAsString;
    }
}
