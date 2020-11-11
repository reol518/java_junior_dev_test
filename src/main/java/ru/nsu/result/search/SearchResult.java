package ru.nsu.result.search;

import ru.nsu.result.Result;

import java.util.List;

public class SearchResult implements Result {
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
