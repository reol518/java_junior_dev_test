package ru.nsu.io;

import com.google.gson.Gson;
import ru.nsu.criterias.InputCriterias;
import ru.nsu.criterias.SearchCriterias;
import ru.nsu.criterias.StatCriteria;

import java.io.FileReader;
import java.io.IOException;

public class InputReaderImpl implements InputReader {
    private final Gson gson;

    public InputReaderImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public InputCriterias readCriterias(String type, String input) throws IOException {
        InputCriterias inputCriterias = null;
        String fileAsString = getFileAsString(input);
        if (type.equals("search")) {
            inputCriterias = readSearchCriterias(fileAsString);
        } else if (type.equals("stat")) {
            inputCriterias = readStatCriteria(fileAsString);
        }
        return inputCriterias;
    }

    private String getFileAsString(String input) throws IOException {
        String fileAsString = "";
        try (FileReader reader = new FileReader(input)) {
            StringBuilder stringBuilder = new StringBuilder();
            int charAsInt;
            while ((charAsInt = reader.read()) != -1) {
                stringBuilder.append((char) charAsInt);
            }
            fileAsString = stringBuilder.toString();
        }
        return fileAsString;
    }

    private SearchCriterias readSearchCriterias(String fileAsString) {
        return gson.fromJson(fileAsString, SearchCriterias.class);
    }

    private StatCriteria readStatCriteria(String fileAsString) {
        return gson.fromJson(fileAsString, StatCriteria.class);
    }


}
