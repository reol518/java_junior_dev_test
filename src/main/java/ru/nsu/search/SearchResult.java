package ru.nsu.search;

import java.util.List;

public class SearchResult {
    private final String type = "search";
    private List<ResultsByCriteria> results;

    public String getType() {
        return type;
    }

    public List<ResultsByCriteria> getResults() {
        return results;
    }

    public void setResults(List<ResultsByCriteria> results) {
        this.results = results;
    }
}
