package ru.nsu.io;

import ru.nsu.error.Error;
import ru.nsu.search.SearchResult;
import ru.nsu.statistics.Statistics;

public interface OutputWriter {
    void saveSearchResult(String outputFile, SearchResult searchResult);
    void saveStatistics(String outputFile, Statistics statistics);
    void outputError(String outputFile, Error error);
}
