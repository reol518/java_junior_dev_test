package ru.nsu;

import ru.nsu.criterias.*;
import ru.nsu.database.DataBaseConnection;
import ru.nsu.result.Result;
import ru.nsu.result.search.Customer;
import ru.nsu.result.search.ResultsByCriteria;
import ru.nsu.result.search.SearchResult;
import ru.nsu.result.statistics.CustomerStat;
import ru.nsu.result.statistics.Purchase;
import ru.nsu.result.statistics.Statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataRequesterImpl implements CustomerDataRequester {
    public CustomerDataRequesterImpl(DataBaseConnection dataBaseConnection) {
        this.connection = dataBaseConnection.getConnection();
    }

    private final Connection connection;

    private List<CustomerStat> customers;
    private int totalSum = 0;

    @Override
    public Result getResult(InputCriterias inputCriterias) throws SQLException {
        Result result = null;
        if (inputCriterias instanceof StatCriteria) {
            result = getStatistics((StatCriteria) inputCriterias);
        } else if (inputCriterias instanceof SearchCriterias) {
            result = getSearchResult(((SearchCriterias) inputCriterias).getCriterias());
        }
        return result;
    }

    private Statistics getStatistics(StatCriteria statCriteria) throws SQLException {
        String querySQL = getSQLQueryForStatCriteria(statCriteria);
        Statement statement = connection.createStatement();
        Statistics statistics = new Statistics();

        ResultSet resultSet = statement.executeQuery(querySQL);

        customers = new ArrayList<>();
        int index = 1;
        while (resultSet.next()) {
            if (resultSet.isFirst())
                statistics.setTotalDays(resultSet.getInt("numOfDays"));

            String name = resultSet.getString("last_name") + " " +
                    resultSet.getString("first_name");
            int customerTotalExpenses = resultSet.getInt("total_sum");

            if (customers.isEmpty()) {
                newCustomer(name, customerTotalExpenses);
            }

            if (isAnotherCustomer(name, index, customerTotalExpenses)) {
                newCustomer(name, customerTotalExpenses);
                index++;
            }
            customers.get(index - 1).addToPurchasesList(newPurchase(resultSet));
        }
        statistics.setParameters(customers, totalSum);

        return statistics;
    }

    private String getSQLQueryForStatCriteria(StatCriteria statCriteria) {
        return "Select customers.last_name, customers.first_name, products.product, " +
                "count(products.id) as product_count, sum(products.price) as product_sum, " +
                "sum(sum(products.price)) over (PARTITION BY customers.last_name, customers.first_name) as total_sum, " +
                "date '" + statCriteria.getEndDate() + "' - date '" + statCriteria.getStartDate() + "' + 1 as numOfDays " +
                "from orders " +
                "join customers ON customers.id = orders.customer_id " +
                "join products ON products.id = orders.product_id " +
                "where orders.buy_date between '" + statCriteria.getStartDate() +
                "' and '" + statCriteria.getEndDate() + "' " +
                "GROUP BY customers.last_name, customers.first_name, " +
                "products.product " +
                "order by total_sum desc, customers.last_name, customers.first_name, product_count";
    }

    private void newCustomer(String name, int customerTotalExpenses) {
        totalSum += customerTotalExpenses;
        List<Purchase> purchases = new ArrayList<>();
        CustomerStat customerStat = new CustomerStat(name, purchases, customerTotalExpenses);
        customers.add(customerStat);
    }

    private boolean isAnotherCustomer(String name, int index, int customerTotalExpenses) {
        return !(customers.get(index - 1).getName().equals(name)
                && customers.get(index - 1).getTotalExpenses() == customerTotalExpenses);
    }

    private Purchase newPurchase(ResultSet resultSet) throws SQLException {
        String purchaseName = resultSet.getString("product");
        int expenses = resultSet.getInt("product_sum");
        return new Purchase(purchaseName, expenses);
    }


    private SearchResult getSearchResult(List<Criteria> criteriaList) throws SQLException {
        String querySQL = null;
        Statement statement = connection.createStatement();
        SearchResult searchResult = new SearchResult();
        List<ResultsByCriteria> resultsForSearchesList = new ArrayList<>();

        for (Criteria criteria : criteriaList) {
            if (criteria instanceof LastNameCriteria) {
                querySQL = getSQLQueryForLastNameCriteria((LastNameCriteria) criteria);
            } else if (criteria instanceof ProductMinTimesBuyingCriteria) {
                querySQL = getSQLQueryForProductMinTimesBuyingCriteria((ProductMinTimesBuyingCriteria) criteria);
            } else if (criteria instanceof CostIntervalCriteria) {
                querySQL = getSQLCostIntervalCriteria((CostIntervalCriteria) criteria);
            } else if (criteria instanceof BadCustomersCriteria) {
                querySQL = getSQLBadCustomersCriteria((BadCustomersCriteria) criteria);
            }

            ResultSet resultSet = statement.executeQuery(querySQL);
            List<Customer> customersList = new ArrayList<>();
            while (resultSet.next()) {
                String customerLastName = resultSet.getString("last_name");
                String customerFirstName = resultSet.getString("first_name");
                customersList.add(new Customer(customerLastName, customerFirstName));
            }
            resultsForSearchesList.add(new ResultsByCriteria(criteria, customersList));
        }
        searchResult.setResults(resultsForSearchesList);

        return searchResult;
    }

    private String getSQLQueryForLastNameCriteria(LastNameCriteria lastNameCriteria) {
        return "SELECT last_name, first_name FROM customers" +
                " WHERE last_name = '"
                + lastNameCriteria.getLastName() + "'";
    }

    private String getSQLQueryForProductMinTimesBuyingCriteria(ProductMinTimesBuyingCriteria productMinTimesBuyingCriteria) {
        return "Select customers.last_name, customers.first_name, products.product, COUNT(products.id) as totalBuying " +
                "from orders " +
                "join customers ON customers.id = orders.customer_id " +
                "join products ON products.id = orders.product_id AND products.product = '"
                + productMinTimesBuyingCriteria.getProductName() + "' " +
                "GROUP BY customers.last_name, customers.first_name, products.id " +
                "having COUNT(products.id) >= " + productMinTimesBuyingCriteria.getMinTimesPurchases();
    }

    private String getSQLCostIntervalCriteria(CostIntervalCriteria costIntervalCriteria) {
        return "Select customers.last_name, customers.first_name, SUM(products.price) " +
                "from orders " +
                "join customers ON customers.id = orders.customer_id " +
                "join products ON products.id = orders.product_id " +
                "GROUP BY customers.last_name, customers.first_name " +
                "having SUM(products.price) between " + costIntervalCriteria.getMinExpenses() +
                " and " + costIntervalCriteria.getMaxExpenses();
    }

    private String getSQLBadCustomersCriteria(BadCustomersCriteria badCustomersCriteria) {
        return "Select customers.last_name, customers.first_name, count(orders.customer_id) " +
                "from orders " +
                "join customers ON customers.id = orders.customer_id " +
                "GROUP BY customers.last_name, customers.first_name " +
                "order by count(orders.customer_id) " +
                "limit " + badCustomersCriteria.getNumOfBadCustomers();
    }
}
