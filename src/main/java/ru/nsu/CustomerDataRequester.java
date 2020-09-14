package ru.nsu;

import ru.nsu.criterias.Criteria;
import ru.nsu.criterias.StatCriteria;
import ru.nsu.search.SearchResult;
import ru.nsu.statistics.Statistics;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDataRequester {
    Statistics getStatistics(StatCriteria statCriteria) throws SQLException;
    SearchResult getSearchResult(List<Criteria> criteriaList) throws SQLException;
}
