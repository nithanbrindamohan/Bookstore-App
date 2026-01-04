/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528projectbackend;

/**
 *
 * @author chasv
 */

public class Customer extends User {
    private customerStatus status;
    private int points = 0;

    // Constructor
    public Customer(String userName, String password, int points) {
        super(userName, password);
        this.status = new silverStatus();
        this.points = points; 
        status.updateStatus(this);
    }

    // Getter methods
    @Override
    public String getName() {
        return super.getName();
    }

    public String getPassword() {
        return super.getPassWord();
    }

    public int getPoints() {
        return points;
    }


    // Setter methods
    public void setUserName(String userName) {
        super.setName(userName);
    }

    public void setPassword(String password) {
        super.setPassWord(password);
    }
    
    public customerStatus getStatus() {
        return status;
    }

    public void setStatus(customerStatus status) {
        this.status = status;
    }

    // Methods to manage points
    public void addPoints(int amount) {
        points += amount;
        status.updateStatus(this);
    }

    public void removePoints(int amount) {
        points -= amount;
        status.updateStatus(this);
    }

    // Method to handle book purchases (no body as per request)
    public void buyBook(int totalCost) {
        this.addPoints(totalCost * 10);
    }
    public int redeemPoints(int totalCost) {
        int redeemableAmount = this.getPoints() / 100; // 100 points = 1 CAD
        int discount = Math.min(redeemableAmount, totalCost);
        totalCost -= discount;
        this.removePoints(discount * 100);
        return totalCost;
    }
}

