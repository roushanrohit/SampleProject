package org.tesco_rule_engine;

public class OrderItem {

    private int productId;
    private String category;
    private int quantity;

    public OrderItem(int productId, String category, int quantity) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
