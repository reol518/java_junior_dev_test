package ru.nsu;

import ru.nsu.criterias.InputCriterias;
import ru.nsu.result.Result;

import java.sql.SQLException;

public interface CustomerDataRequester {
    Result getResult(InputCriterias inputCriterias) throws  SQLException;
}
