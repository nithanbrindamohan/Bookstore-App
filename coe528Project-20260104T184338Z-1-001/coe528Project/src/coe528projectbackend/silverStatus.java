/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528projectbackend;

/**
 *
 * @author chasv
 */
public class silverStatus implements customerStatus {
    @Override
    public void updateStatus(Customer customer) {
        if (customer.getPoints() >= 1000) {
            customer.setStatus(new GoldStatus());
        }
    }

    @Override
    public String getStatus() {
        return "Silver";
    }
}
