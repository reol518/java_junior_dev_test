package ru.nsu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.criterias.Criteria;
import ru.nsu.database.DataBaseConnectionImpl;
import ru.nsu.io.InputReaderImpl;
import ru.nsu.io.OutputWriterImpl;
import ru.nsu.serialization.CriteriasDeserializer;
import ru.nsu.serialization.CriteriasSerializer;

public class Main {
    public static void main(String[] args) {
        String type = args.length >= 1 ? args[0] : null;
        String inputFile = args.length >= 2 ? args[1] : null;
        String outputFile = args.length >= 3 ? args[2] : null;

        DataBaseConnectionImpl dataBaseConnectionImpl = new DataBaseConnectionImpl();
        CustomerDataRequesterImpl customerDataRequesterImpl = new CustomerDataRequesterImpl(dataBaseConnectionImpl);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Criteria.class, new CriteriasDeserializer())
                .registerTypeAdapter(Criteria.class, new CriteriasSerializer())
                .create();
        OutputWriterImpl outputWriterImpl = new OutputWriterImpl(gson);
        InputReaderImpl inputReaderImpl = new InputReaderImpl(gson);

        DatabaseService databaseService = new DatabaseService(customerDataRequesterImpl, outputWriterImpl, inputReaderImpl);
        databaseService.start(type, inputFile, outputFile);
    }
}