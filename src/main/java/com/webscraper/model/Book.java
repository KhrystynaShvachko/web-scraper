package com.webscraper.model;

public class Book {
    private String title;
    private double price;
    private String availability;
    private int availableQuantity;
    private int rating;
    private String detailUrl;
    private String upc;

    public Book() {
    }

    public Book(String title, double price, String availability, int availableQuantity, int rating, String detailUrl, String upc) {
        this.title = title;
        this.price = price;
        this.availability = availability;
        this.availableQuantity = availableQuantity;
        this.rating = rating;
        this.detailUrl = detailUrl;
        this.upc = upc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }
}
