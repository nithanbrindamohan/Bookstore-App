/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapp; // Defines the package for the class

/**
 * CustomerStartScreen represents the main screen for customers in the bookstore application.
 * It displays available books, allows selection for purchase, and provides options 
 * to buy with or without redeeming points.
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
import javafx.collections.FXCollections; // Imports FXCollections for managing observable lists
import javafx.collections.ObservableList; // Imports ObservableList to track book selections
import javafx.scene.control.*; // Imports JavaFX UI components such as TableView, Label, and Button
import javafx.scene.control.cell.CheckBoxTableCell; // Imports CheckBoxTableCell for book selection
import javafx.scene.control.cell.PropertyValueFactory; // Imports PropertyValueFactory for table columns
import javafx.scene.layout.*; // Imports JavaFX layout components
import javafx.stage.Stage; // Imports Stage to manage application windows
import javafx.scene.Scene; // Imports Scene for UI content
import javafx.geometry.Pos; // Imports Pos for UI alignment
import javafx.geometry.Insets; // Imports Insets to set padding and margins

// Defines a class extending BorderPane, a JavaFX layout container
public class CustomerStartScreen extends BorderPane {
    private TableView<Book> booksTable = new TableView<>(); // Table to display books available for purchase
    private Stage primaryStage; // Stores the main application window reference
    private Customer currentCustomer; // Stores the logged-in customer details

    /**
     * Constructor for CustomerStartScreen.
     * 
     * @param primaryStage The main stage of the application.
     * @param customer The currently logged-in customer.
     */
    public CustomerStartScreen(Stage primaryStage, Customer customer) {
        this.primaryStage = primaryStage; // Assigns the primary stage reference
        this.currentCustomer = customer; // Assigns the current customer reference

        // Creates a welcome message displaying the customer's username, points, and status
        Label welcomeLabel = new Label(String.format("Welcome %s. You have %d points. Status: %s",
            currentCustomer.getUsername(),
            currentCustomer.getPoints(),
            currentCustomer.getStatus()));
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"); // Styles the welcome label

        // Creates a table column for book names
        TableColumn<Book, String> nameCol = new TableColumn<>("Book Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Binds column to the Book's name property
        nameCol.setPrefWidth(200); // Sets column width

        // Creates a table column for book prices
        TableColumn<Book, Double> priceCol = new TableColumn<>("Book Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price")); // Binds column to the Book's price property
        priceCol.setPrefWidth(100); // Sets column width

        // Creates a table column with checkboxes for book selection
        TableColumn<Book, Boolean> selectCol = new TableColumn<>("Select");
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selected")); // Binds column to selection property
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol)); // Uses checkboxes for selection
        selectCol.setPrefWidth(80); // Sets column width
        selectCol.setEditable(true); // Allows editing (checkbox selection)

        // Adds all columns to the table
        booksTable.getColumns().addAll(nameCol, priceCol, selectCol);

        // Populates the table with sample book data
        booksTable.setItems(FXCollections.observableArrayList(
            new Book("Java101", 99.99),
            new Book("Harry Potter", 7.49),
            new Book("Finding Nemo", 24.48)
        ));

        booksTable.setEditable(true); // Enables table editing
        booksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Adjusts column width automatically

        // Creates a button to buy books without redeeming points
        Button buyButton = new Button("Buy");
        buyButton.setOnAction(e -> handlePurchase(false)); // Calls handlePurchase with false (no point redemption)

        // Creates a button to buy books using redeemed points
        Button redeemButton = new Button("Redeem points and Buy");
        redeemButton.setOnAction(e -> handlePurchase(true)); // Calls handlePurchase with true (redeem points)

        // Creates a logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> new LoginScreen(primaryStage).show()); // Returns to the login screen

        // Creates a horizontal layout for buttons with spacing of 10 pixels
        HBox buttonBox = new HBox(10, buyButton, redeemButton, logoutButton);
        buttonBox.setAlignment(Pos.CENTER); // Centers buttons horizontally

        // Creates a vertical layout to structure the UI components
        VBox layout = new VBox(15, welcomeLabel, booksTable, buttonBox);
        layout.setAlignment(Pos.CENTER); // Centers all elements in the VBox
        layout.setPadding(new Insets(20)); // Adds padding around the layout

        // Sets the layout as the center content of the BorderPane
        this.setCenter(layout);
    }

    /**
     * Handles the purchase process by collecting selected books and proceeding to the summary screen.
     * 
     * @param redeemPoints Whether the customer is redeeming points for the purchase.
     */
    private void handlePurchase(boolean redeemPoints) {
        ObservableList<Book> selectedBooks = FXCollections.observableArrayList(); // List to store selected books

        // Loops through the books in the table and adds selected ones to the list
        for (Book book : booksTable.getItems()) {
            if (book.isSelected()) {
                selectedBooks.add(book);
            }
        }

        // If at least one book is selected, proceed to the cost summary screen
        if (!selectedBooks.isEmpty()) {
            new CustomerCostScreen(primaryStage, selectedBooks, currentCustomer, redeemPoints).show();
        } else {
            // Show an alert if no books were selected
            showAlert("No Selection", "You must select at least one book to purchase.");
        }
    }

    /**
     * Displays an alert message dialog.
     * 
     * @param title The title of the alert window.
     * @param message The content of the alert message.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING); // Creates a warning alert
        alert.setTitle(title); // Sets alert title
        alert.setHeaderText(null); // Removes header text
        alert.setContentText(message); // Sets alert message
        alert.showAndWait(); // Displays the alert and waits for user response
    }

    /**
     * Displays the customer start screen.
     */
    public void show() {
        Scene scene = new Scene(this, 600, 400); // Creates a scene with dimensions 600x400 pixels
        primaryStage.setScene(scene); // Sets the scene on the primary stage
        primaryStage.setTitle("Bookstore App"); // Sets the window title
        primaryStage.show(); // Displays the window
    }
}
