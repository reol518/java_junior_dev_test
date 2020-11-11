package ru.nsu.result.statistics;

import java.util.List;

public class CustomerStat {
    public CustomerStat(String name, List<Purchase> purchases, int totalExpenses) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpenses = totalExpenses;
    }

    private String name;
    private List<Purchase> purchases;
    private int totalExpenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addToPurchasesList(Purchase purchase) {
        purchases.add(purchase);
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
