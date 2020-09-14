package ru.nsu.criterias;

public class ProductBuyingCriteria extends Criteria {
    public ProductBuyingCriteria(String productName, int minTimesBuy) {
        this.productName = productName;
        this.minTimesBuy = minTimesBuy;
    }
    private String productName;
    private int minTimesBuy;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMinTimesBuy() {
        return minTimesBuy;
    }

    public void setMinTimesBuy(int minTimesBuy) {
        this.minTimesBuy = minTimesBuy;
    }
}
