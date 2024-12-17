package com.example.jakarta.hello;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product() {}
    public Product(String name, double price) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name must be 100 characters or less");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name must be 100 characters or less");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "{\"id\": " + id + ", \"name\": \"" + name + "\", \"price\": " + price + "}";
    }
}