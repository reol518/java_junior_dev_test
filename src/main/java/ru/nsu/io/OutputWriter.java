package ru.nsu.io;

import ru.nsu.error.Error;
import ru.nsu.result.Result;

import java.io.IOException;

public interface OutputWriter {
    void saveResult(String outputFile, Result result) throws  IOException;

    void errorOutput(String outputFile, Error error) ;
}
