package ru.nsu.io;

import ru.nsu.criterias.InputCriterias;

import java.io.IOException;

public interface InputReader {
    InputCriterias readCriterias(String type, String input) throws IOException;
}
