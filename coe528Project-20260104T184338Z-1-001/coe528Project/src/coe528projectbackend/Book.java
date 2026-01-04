/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528projectbackend;

/**
 *
 * @author chasv
 */
public class Book {
    private String title;
    private String author;
    private double price;

    // Constructor
    public Book(String title, String author, int cost) {
        this.title = title;
        this.author = author;
        this.price = cost;
    }

    public double getPrice() {
        return price;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Setter methods
    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Method to display book details
    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Price: " + price);
    }
}

