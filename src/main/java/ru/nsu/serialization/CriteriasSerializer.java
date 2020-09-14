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
            JsonObject obj = new JsonObject();
            obj.addProperty("lastName", lastNameCriteria.getLastName());
            return obj;
        }
        if (criteria instanceof IntervalTotalSumCriteria) {
            IntervalTotalSumCriteria intervalTotalSumCriteria = (IntervalTotalSumCriteria) criteria;
            JsonObject obj = new JsonObject();
            obj.addProperty("minExpenses", intervalTotalSumCriteria.getMinExpenses());
            obj.addProperty("maxExpenses", intervalTotalSumCriteria.getMaxExpenses());
            return obj;
        }
        if (criteria instanceof ProductBuyingCriteria) {
            ProductBuyingCriteria productBuyingCriteria = (ProductBuyingCriteria) criteria;
            JsonObject obj = new JsonObject();
            obj.addProperty("productName", productBuyingCriteria.getProductName());
            obj.addProperty("minTimes", productBuyingCriteria.getMinTimesBuy());
            return obj;
        }
        if (criteria instanceof BadCustomersCriteria) {
            BadCustomersCriteria badCustomersCriteria = (BadCustomersCriteria) criteria;
            JsonObject obj = new JsonObject();
            obj.addProperty("badCustomers", badCustomersCriteria.getNumOfBadCustomers());
            return obj;
        }
        return null;
    }
}
