package ru.nsu.io;

import com.google.gson.Gson;
import ru.nsu.error.Error;
import ru.nsu.search.SearchResult;
import ru.nsu.statistics.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


public class OutputWriterImpl implements OutputWriter {
    private final Gson gson;

    public OutputWriterImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void saveSearchResult(String outputFile, SearchResult searchResult)  {
        String json = gson.toJson(searchResult);
        writeInFile(outputFile, json);
        System.out.println(json);
    }

    @Override
    public void saveStatistics(String outputFile, Statistics statistics) {
        String json = gson.toJson(statistics);
        writeInFile(outputFile, json);
        System.out.println(json);
    }

    @Override
    public void outputError(String outputFile, Error error) {
        String json = gson.toJson(error);
        writeInFile(outputFile, json);
        System.out.println(json);
    }

    private void writeInFile(String outputFile, String json) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            writer.write(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
