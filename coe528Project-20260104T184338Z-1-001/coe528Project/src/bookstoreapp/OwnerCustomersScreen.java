/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapp;

/**
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.io.IOException;

public class OwnerCustomersScreen extends BorderPane {
    // Table to display customer information
    private TableView<Customer> customersTable = new TableView<>();
    private Stage primaryStage;
    private ObservableList<Customer> customers;

    public OwnerCustomersScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        // Sample customer data
        this.customers = FXCollections.observableArrayList(
            new Customer("BookReader9000", "password123", 750),
            new Customer("iLUVbooks", "abcdef", 1200)
        );

        // Column for usernames
        TableColumn<Customer, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setPrefWidth(200);

        // Column for passwords
        TableColumn<Customer, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setPrefWidth(200);

        // Column for loyalty points
        TableColumn<Customer, Integer> pointsCol = new TableColumn<>("Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        pointsCol.setPrefWidth(100);

        // Add columns to table
        customersTable.getColumns().addAll(usernameCol, passwordCol, pointsCol);
        customersTable.setItems(customers);
        customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Input fields and labels for adding customers
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();

        // Button to add new customers
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();

                // Ensure input fields are not empty
                if (!username.isEmpty() && !password.isEmpty()) {
                    customers.add(new Customer(username, password, 0));
                    try {
                        FileHandler.saveCustomers(customers);
                        usernameField.clear();
                        passwordField.clear();
                    } catch (IOException ex) {
                        showAlert("Save Error", "Could not save customer data: " + ex.getMessage());
                    }
                } else {
                    showAlert("Input Error", "Username and password cannot be empty");
                }
            } catch (Exception ex) {
                showAlert("Error", "Invalid input format");
            }
        });

        // Layout for input fields
        VBox usernameBox = new VBox(5, usernameLabel, usernameField);
        VBox passwordBox = new VBox(5, passwordLabel, passwordField);
        HBox inputSection = new HBox(10, usernameBox, passwordBox, addButton);
        inputSection.setAlignment(Pos.CENTER);

        // Button to delete selected customer
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Customer selected = customersTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customers.remove(selected);
                try {
                    FileHandler.saveCustomers(customers);
                } catch (IOException ex) {
                    showAlert("Save Error", "Could not save customer data: " + ex.getMessage());
                }
            } else {
                showAlert("Selection Error", "You must select a customer to delete");
            }
        });

        // Button to navigate back to previous screen
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new OwnerStartScreen(primaryStage).show());

        // Layout for action buttons
        HBox bottomButtons = new HBox(10, addButton, deleteButton, backButton);
        bottomButtons.setAlignment(Pos.CENTER);

        // Main layout configuration
        VBox layout = new VBox(15, customersTable, inputSection, bottomButtons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        this.setCenter(layout);
    }

    // Method to display alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to show the customer management screen
    public void show() {
        Scene scene = new Scene(this, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bookstore App");
        primaryStage.show();
    }
}
