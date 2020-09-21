package ru.nsu.criterias;

public class ProductMinTimesBuyingCriteria implements Criteria {
    public ProductMinTimesBuyingCriteria(String productName, int minTimesPurchases) {
        this.productName = productName;
        this.minTimesPurchases = minTimesPurchases;
    }

    private String productName;
    private int minTimesPurchases;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimesPurchases() {
        return minTimesPurchases;
    }

    public void setMinTimesPurchases(int minTimesPurchases) {
        this.minTimesPurchases = minTimesPurchases;
    }
}
