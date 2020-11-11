package ru.nsu.result.statistics;

import ru.nsu.result.Result;

import java.util.List;

public class Statistics implements Result {
    private final String type = "stat";
    private int totalDays;
    private List<CustomerStat> customers;
    private int totalExpenses;
    private double avgExpenses;

    public String getType() {
        return type;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public List<CustomerStat> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerStat> customers) {
        this.customers = customers;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    public void setParameters(List<CustomerStat> customers, int totalSum) {
        this.customers = customers;
        this.totalExpenses = totalSum;
        this.avgExpenses = (double) totalSum / customers.size();
    }
}
