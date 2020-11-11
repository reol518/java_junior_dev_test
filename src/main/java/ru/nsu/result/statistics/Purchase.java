package ru.nsu.result.statistics;

public class Purchase {
    public Purchase(String productName, int expenses) {
        this.productName = productName;
        this.expenses = expenses;
    }

    private String productName;
    private int expenses;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }
}
