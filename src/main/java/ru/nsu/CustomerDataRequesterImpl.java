package ru.nsu;

import ru.nsu.criterias.*;
import ru.nsu.database.DataBaseConnection;
import ru.nsu.search.Customer;
import ru.nsu.search.ResultsForSearch;
import ru.nsu.search.SearchResult;
import ru.nsu.statistics.CustomerStat;
import ru.nsu.statistics.Purchase;
import ru.nsu.statistics.Statistics;

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

    @Override
    public Statistics getStatistics(StatCriteria statCriteria) throws SQLException {
        String querySQL = getSQLQueryForStatCriteria(statCriteria);
        Statement statement = connection.createStatement();
        Statistics statistics = new Statistics();

        ResultSet resultSet = statement.executeQuery(querySQL);

        List<CustomerStat> customers = new ArrayList<CustomerStat>();

        int index = 1;
        int totalSum = 0;
        while (resultSet.next()) {
            if (resultSet.isFirst())
                statistics.setTotalDays(resultSet.getInt("numOfDays"));

            String name = resultSet.getString("last_name") + " " +
                    resultSet.getString("first_name");
            int totalExpenses = resultSet.getInt("total_sum");
            CustomerStat customerStat;
            List<Purchase> purchases;

            if (customers.isEmpty()) {
                purchases = new ArrayList<Purchase>();
                customerStat = new CustomerStat(name, purchases, totalExpenses);
                customers.add(customerStat);
                totalSum += totalExpenses;
            }
            if (customers.get(index - 1).getName().equals(name)
                    && customers.get(index - 1).getTotalExpenses() == totalExpenses
            ) {
                customers.get(index - 1).addToPurchasesList(newPurchase(resultSet));

            } else if (!customers.get(index - 1).getName().equals(name)
                    && customers.get(index - 1).getTotalExpenses() != totalExpenses
            ) {

                totalSum += totalExpenses;
                index++;
                purchases = new ArrayList<Purchase>();
                customerStat = new CustomerStat(name, purchases, totalExpenses);
                customers.add(customerStat);

                customers.get(index - 1).addToPurchasesList(newPurchase(resultSet));

            }
        }
        statistics.setCustomers(customers);
        statistics.setTotalExpenses(totalSum);
        double avgExpenses = (double) totalSum / customers.size();
        statistics.setAvgExpenses(avgExpenses);
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

    private Purchase newPurchase(ResultSet resultSet) throws SQLException {
        String purchaseName = resultSet.getString("product");
        int expenses = resultSet.getInt("product_sum");
        return new Purchase(purchaseName, expenses);
    }

    @Override
    public SearchResult getSearchResult(List<Criteria> criteriaList) throws SQLException {
        String querySQL = null;
        Statement statement = connection.createStatement();
        SearchResult searchResult = new SearchResult();
        List<ResultsForSearch> resultsForSearchesList = new ArrayList<ResultsForSearch>();

        for (Criteria criteria : criteriaList) {

            if (criteria instanceof LastNameCriteria) {
                querySQL = getSQLQueryForLastNameCriteria((LastNameCriteria) criteria);
            } else if (criteria instanceof ProductBuyingCriteria) {
                querySQL = getSQLQueryForProductBuyingMinTimes((ProductBuyingCriteria) criteria);
            } else if (criteria instanceof IntervalTotalSumCriteria) {
                querySQL = getSQLIntervalTotalSumCriteria((IntervalTotalSumCriteria) criteria);
            } else if (criteria instanceof BadCustomersCriteria) {
                querySQL = getSQLBadCustomersCriteria((BadCustomersCriteria) criteria);
            }
            ResultSet resultSet = statement.executeQuery(querySQL);
            List<Customer> customersList = new ArrayList<Customer>();
            while (resultSet.next()) {
                String customerLastName = resultSet.getString("last_name");
                String customerFirstName = resultSet.getString("first_name");
                customersList.add(new Customer(customerLastName, customerFirstName));
            }
            resultsForSearchesList.add(new ResultsForSearch(criteria, customersList));
        }
        searchResult.setResults(resultsForSearchesList);

        return searchResult;
    }

    private String getSQLQueryForLastNameCriteria(LastNameCriteria lastNameCriteria) {
        return "SELECT last_name, first_name FROM customers" +
                " WHERE last_name = '"
                + lastNameCriteria.getLastName() + "'";
    }

    private String getSQLQueryForProductBuyingMinTimes(ProductBuyingCriteria productBuyingCriteria) {
        return "Select customers.last_name, customers.first_name, products.product, COUNT(products.id) as totalBuying " +
                "from orders " +
                "join customers ON customers.id = orders.customer_id " +
                "join products ON products.id = orders.product_id AND products.product = '"
                + productBuyingCriteria.getProductName() + "' " +
                "GROUP BY customers.last_name, customers.first_name, products.id " +
                "having COUNT(products.id) >= " + productBuyingCriteria.getMinTimesBuy();
    }

    private String getSQLIntervalTotalSumCriteria(IntervalTotalSumCriteria intervalTotalSumCriteria) {
        return "Select customers.last_name, customers.first_name, SUM(products.price) " +
                "from orders " +
                "join customers ON customers.id = orders.customer_id " +
                "join products ON products.id = orders.product_id " +
                "GROUP BY customers.last_name, customers.first_name " +
                "having SUM(products.price) between " + intervalTotalSumCriteria.getMinExpenses() +
                " and " + intervalTotalSumCriteria.getMaxExpenses();
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
