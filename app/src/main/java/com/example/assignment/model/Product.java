package com.example.assignment.model;

public class Product {

    private String product_name,product_detail;

    public Product(String product_name,String product_detail) {
        this.product_name=product_name;
        this.product_detail=product_detail;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
