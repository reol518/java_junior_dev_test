package ru.nsu.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.nsu.criterias.*;

import java.lang.reflect.Type;

public class CriteriasSerializer implements JsonSerializer<Criteria> {
    @Override
    public JsonElement serialize(Criteria criteria, Type type, JsonSerializationContext jsonSerializationContext) {
        if (criteria instanceof LastNameCriteria) {
            LastNameCriteria lastNameCriteria = (LastNameCriteria) criteria;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("lastName", lastNameCriteria.getLastName());
            return jsonObject;
        }

        if (criteria instanceof CostIntervalCriteria) {
            CostIntervalCriteria costIntervalCriteria = (CostIntervalCriteria) criteria;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("minExpenses", costIntervalCriteria.getMinExpenses());
            jsonObject.addProperty("maxExpenses", costIntervalCriteria.getMaxExpenses());
            return jsonObject;
        }

        if (criteria instanceof ProductMinTimesBuyingCriteria) {
            ProductMinTimesBuyingCriteria productMinTimesBuyingCriteria = (ProductMinTimesBuyingCriteria) criteria;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("productName", productMinTimesBuyingCriteria.getProductName());
            jsonObject.addProperty("minTimes", productMinTimesBuyingCriteria.getMinTimesPurchases());
            return jsonObject;
        }

        if (criteria instanceof BadCustomersCriteria) {
            BadCustomersCriteria badCustomersCriteria = (BadCustomersCriteria) criteria;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("badCustomers", badCustomersCriteria.getNumOfBadCustomers());
            return jsonObject;
        }

        return null;
    }
}
