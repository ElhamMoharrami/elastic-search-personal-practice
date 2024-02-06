package com.practice.model;

public class Product {
    private String id;
    private String title;
    private String type;
    private Double price;

    public Product() {
    }


    public Product(String id, String title, String type, Double price) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
