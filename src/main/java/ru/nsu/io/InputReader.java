package ru.nsu.io;

import ru.nsu.criterias.SearchCriterias;
import ru.nsu.criterias.StatCriteria;

import java.io.IOException;

public interface InputReader {
    SearchCriterias readSearchCriterias(String input) throws IOException;
    StatCriteria readStatCriteria(String input) throws IOException;
}
