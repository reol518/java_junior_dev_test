package ru.nsu;

import ru.nsu.criterias.SearchCriterias;
import ru.nsu.criterias.StatCriteria;
import ru.nsu.error.Error;
import ru.nsu.io.InputReader;
import ru.nsu.io.OutputWriter;
import ru.nsu.search.SearchResult;
import ru.nsu.statistics.Statistics;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseService {
    private final CustomerDataRequester customerDataRequester;
    private final OutputWriter outputWriter;
    private final InputReader inputReader;

    public DatabaseService(CustomerDataRequester customerDataRequester, OutputWriter outputWriter, InputReader inputReader) {
        this.customerDataRequester = customerDataRequester;
        this.outputWriter = outputWriter;
        this.inputReader = inputReader;
    }


    public void start(String type, String inputFile, String outputFile) {
        if (outputFile == null) {
            outputWriter.errorOutput("OutputError.json", new Error("Null аргумент"));
        } else if (type == null || inputFile == null) {
            outputWriter.errorOutput(outputFile, new Error("Null аргумент"));
        } else {
            try {
                if (type.equals("search")) {
                    SearchCriterias searchCriterias = inputReader.readSearchCriterias(inputFile);
                    SearchResult searchResult = customerDataRequester.getSearchResult(searchCriterias.getCriterias());
                    outputWriter.saveSearchResult(outputFile, searchResult);
                } else if (type.equals("stat")) {
                    StatCriteria statCriteria = inputReader.readStatCriteria(inputFile);
                    Statistics statistics = customerDataRequester.getStatistics(statCriteria);
                    outputWriter.saveStatistics(outputFile, statistics);
                } else {
                    outputWriter.errorOutput(outputFile, new Error("Некорректный аргумент"));
                }

            } catch (SQLException e) {
                outputWriter.errorOutput(outputFile, new Error("Ошибка при работе с базой данных"));
            } catch (IOException e) {
                outputWriter.errorOutput(outputFile, new Error("Ошибка при работе с файлами"));
            } catch (Exception e) {
                outputWriter.errorOutput(outputFile, new Error("Произошла ошибка в работе программы"));
            }
        }
    }
}