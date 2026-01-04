/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528projectbackend;

/**
 *
 * @author chasv
 */

public class Owner extends User {
    private static Owner instance;

    // Private constructor to enforce Singleton pattern
    private Owner() {
        super("admin", "admin"); // Calls the User constructor
    }

    // Singleton pattern method to get the only instance of Owner
    public static Owner getInstance() {
        if (instance == null) {
            instance = new Owner();
        }
        return instance;
    }

    // Method to add a customer
    public void addCustomer() {
        System.out.println("Customer added.");
    }

    // Method to remove a customer
    public void removeCustomer() {
        System.out.println("Customer removed.");
    }

    // Method to add a book
    public void addBook() {
        System.out.println("Book added to inventory.");
    }

    // Method to remove a book
    public void removeBook() {
        System.out.println("Book removed from inventory.");
    }

    // Method to display Owner details
    public void displayOwnerInfo() {
        System.out.println("Owner Username: " + getName());
    }
}
