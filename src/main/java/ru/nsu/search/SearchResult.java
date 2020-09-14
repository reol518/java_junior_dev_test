package ru.nsu.search;

import java.util.List;

public class SearchResult {
    private final String type = "search";
    private List<ResultsForSearch> results;

    public String getType() {
        return type;
    }

    public List<ResultsForSearch> getResults() {
        return results;
    }

    public void setResults(List<ResultsForSearch> results) {
        this.results = results;
    }
}
