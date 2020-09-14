package ru.nsu.statistics;

public class Purchase {
    public Purchase(String name, int expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    private String name;
    private int expenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }
}
