package ru.nsu.io;

import ru.nsu.error.Error;
import ru.nsu.search.SearchResult;
import ru.nsu.statistics.Statistics;

import java.io.IOException;

public interface OutputWriter {
    void saveSearchResult(String outputFile, SearchResult searchResult) throws IOException;

    void saveStatistics(String outputFile, Statistics statistics) throws IOException;

    void errorOutput(String outputFile, Error error) ;
}
