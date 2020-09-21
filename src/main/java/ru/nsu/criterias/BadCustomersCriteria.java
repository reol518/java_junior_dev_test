package ru.nsu.criterias;

public class BadCustomersCriteria implements Criteria {
    public BadCustomersCriteria(int numOfBadCustomers) {
        this.numOfBadCustomers = numOfBadCustomers;
    }

    private int numOfBadCustomers;

    public int getNumOfBadCustomers() {
        return numOfBadCustomers;
    }

    public void setNumOfBadCustomers(int numOfBadCustomers) {
        this.numOfBadCustomers = numOfBadCustomers;
    }
}
