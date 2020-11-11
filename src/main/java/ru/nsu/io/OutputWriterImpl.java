package ru.nsu.io;

import com.google.gson.Gson;
import ru.nsu.error.Error;
import ru.nsu.result.Result;

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
    public void saveResult(String outputFile, Result result) throws IOException {
        String json = gson.toJson(result);
        System.out.println(json);
        writeInFile(outputFile, json);
    }

    @Override
    public void errorOutput(String outputFile, Error error)  {
        String json = gson.toJson(error);
        try {
            writeInFile(outputFile, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    private void writeInFile(String outputFile, String json) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
            writer.write(json);
        }
    }
}
