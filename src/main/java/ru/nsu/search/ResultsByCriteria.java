package ru.nsu.search;

import ru.nsu.criterias.Criteria;

import java.util.List;

public class ResultsByCriteria {
    public ResultsByCriteria(Criteria criteria, List<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }

    private Criteria criteria;
    private List<Customer> results;

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<Customer> getResults() {
        return results;
    }

    public void setResults(List<Customer> results) {
        this.results = results;
    }
}
