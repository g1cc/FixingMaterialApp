package com.example.fixingmaterial.Domain;

import java.io.Serializable;

public class StockDomain implements Serializable
{
    private String title;
    private String image;
    private String description;
    private String brand;
    private int price;
    private int cartPosition;

    public StockDomain(String title, String image, String description, String brand, int price) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCartPosition() {
        return cartPosition;
    }

    public void setCartPosition(int cartPosition) {
        this.cartPosition = cartPosition;
    }
}
