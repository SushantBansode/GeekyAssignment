package com.example.assignment.model;

/**
 * Created by user on 11/22/2017.
 */

public class Order {

    private int ProductId;
    private String ProductName;
    private String ProductPrice;
    private int categoryId;

    public Order(int categoryId,int productId, String productName, String ProductPrice) {
        this.categoryId=categoryId;
        this.ProductId = productId;
        this.ProductName = productName;
        this.ProductPrice = ProductPrice;
    }


    public int getCategoryId() { return categoryId; }

    public int getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getPrice() {
        return ProductPrice;
    }
}
