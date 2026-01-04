/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapp; // Defines the package for the class

/**
 * Class representing a customer cost summary screen in a bookstore application.
 * Displays the total cost of selected books, updates customer points, and 
 * provides a logout option.
 * 
 * @author dian
 */

import coe528projectbackend.Book; // Imports the Book class from the model package
import coe528projectbackend.Customer; // Imports the Customer class from the model package
import coe528projectbackend.BookStore;
import coe528projectbackend.GoldStatus;
import coe528projectbackend.Owner;
import coe528projectbackend.User;
import coe528projectbackend.customerStatus;
import coe528projectbackend.silverStatus;
import javafx.collections.ObservableList; // Imports ObservableList for handling book selection
import javafx.scene.control.*; // Imports JavaFX UI components like Label and Button
import javafx.scene.layout.*; // Imports layout components for UI structuring
import javafx.stage.Stage; // Imports Stage for managing application windows
import javafx.scene.Scene; // Imports Scene to define UI content
import javafx.geometry.Pos; // Imports Pos to align UI elements

// Defines a class that extends BorderPane, a layout container for JavaFX UI
public class CustomerCostScreen extends BorderPane {
    private Stage primaryStage; // Stores the main application window

    /**
     * Constructor for CustomerCostScreen.
     * 
     * @param primaryStage The main stage of the application.
     * @param selectedBooks The list of books selected for purchase.
     * @param customer The customer making the purchase.
     * @param redeemPoints A flag indicating whether to redeem customer points.
     */
    public CustomerCostScreen(Stage primaryStage, ObservableList<Book> selectedBooks, 
                              Customer customer, boolean redeemPoints) {
        this.primaryStage = primaryStage; // Assigns the primary stage reference

        // Calculates the total cost of selected books
        double total = selectedBooks.stream().mapToDouble(Book::getPrice).sum();

        // Calculates points earned from the purchase (10 points per dollar spent)
        int pointsEarned = (int)(total * 10);

        // If redeeming points, apply discount and update customer points
        if (redeemPoints) {
            double discount = Math.min(total, customer.getPoints() / 100.0); // Determines maximum discount
            total -= discount; // Reduces total by discount amount
            customer.setPoints(customer.getPoints() - (int)(discount * 100) + pointsEarned); // Updates points
        } else {
            customer.setPoints(customer.getPoints() + pointsEarned); // Adds earned points without redemption
        }

        // Creates labels to display total cost and customer points status
        Label totalLabel = new Label(String.format("Total Cost: $%.2f", total));
        Label pointsLabel = new Label(String.format("Points: %d, Status: %s", 
            customer.getPoints(), customer.getStatus()));

        // Creates a logout button
        Button logoutBtn = new Button("Logout");

        // Sets the logout button action to return to the login screen
        logoutBtn.setOnAction(e -> new LoginScreen(primaryStage).show());

        // Creates a vertical layout (VBox) and adds UI components
        VBox layout = new VBox(20, totalLabel, pointsLabel, logoutBtn);
        layout.setAlignment(Pos.CENTER); // Centers elements in the layout

        // Sets the layout to the center of the BorderPane
        this.setCenter(layout);
    }

    /**
     * Displays the customer cost summary screen.
     */
    public void show() {
        Scene scene = new Scene(this, 400, 300); // Creates a scene with specified dimensions
        primaryStage.setScene(scene); // Sets the scene for the primary stage
        primaryStage.setTitle("Bookstore App"); // Sets the window title
        primaryStage.show(); // Displays the stage
    }
}
