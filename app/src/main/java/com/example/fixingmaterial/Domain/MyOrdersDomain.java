package com.example.fixingmaterial.Domain;

public class MyOrdersDomain {

    private String title, date;
    private int totalPrice, deliveryPrice, orderPrice;


    public MyOrdersDomain(String title, String date, int totalPrice, int deliveryPrice, int orderPrice) {
        this.title = title;
        this.date = date;
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
        this.orderPrice = orderPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
