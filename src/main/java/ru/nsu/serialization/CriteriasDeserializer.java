package ru.nsu.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ru.nsu.criterias.*;

import java.lang.reflect.Type;

public class CriteriasDeserializer implements JsonDeserializer<Criteria> {
    public Criteria deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement lastName = jsonElement.getAsJsonObject().get("lastName");
        if (lastName != null) {
            return new LastNameCriteria(lastName.getAsString());
        }
        JsonElement minExpenses = jsonElement.getAsJsonObject().get("minExpenses");
        if (minExpenses != null) {
            int maxExpenses = jsonElement.getAsJsonObject().get("maxExpenses").getAsInt();
            return new IntervalTotalSumCriteria(minExpenses.getAsInt(), maxExpenses);
        }
        JsonElement productName = jsonElement.getAsJsonObject().get("productName");
        if (productName != null) {
            int minTimes = jsonElement.getAsJsonObject().get("minTimes").getAsInt();
            return new ProductBuyingCriteria(productName.getAsString(), minTimes);
        }
        JsonElement badCustomers = jsonElement.getAsJsonObject().get("badCustomers");
        if (badCustomers != null) {
            return new BadCustomersCriteria(badCustomers.getAsInt());
        }
        return null;
    }
}
