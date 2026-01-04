package bookstoreapp;

import coe528projectbackend.Book; // Imports the Book class from the model package
import coe528projectbackend.Customer; // Imports the Customer class from the model package
import coe528projectbackend.BookStore;
import coe528projectbackend.GoldStatus;
import coe528projectbackend.Owner;
import coe528projectbackend.User;
import coe528projectbackend.customerStatus;
import coe528projectbackend.silverStatus;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OwnerStartScreen extends VBox {
    private Stage primaryStage; // Reference to the primary stage

    public OwnerStartScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        // Create and style the welcome label
        Label welcomeLabel = new Label("Welcome, Owner!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // Create buttons for navigation
        Button booksBtn = new Button("Books"); // Navigate to book management
        Button customersBtn = new Button("Customers"); // Navigate to customer management
        Button logoutBtn = new Button("Logout"); // Logout and return to login screen
        
        // Set button width for consistency
        booksBtn.setPrefWidth(200);
        customersBtn.setPrefWidth(200);
        logoutBtn.setPrefWidth(200);

        // Define button actions
        booksBtn.setOnAction(e -> new OwnerBooksScreen(primaryStage).show()); // Open books screen
        logoutBtn.setOnAction(e -> new LoginScreen(primaryStage).show()); // Return to login screen
        customersBtn.setOnAction(e -> new OwnerCustomersScreen(primaryStage).show()); // Open customers screen

        // Add elements to the layout
        this.getChildren().addAll(
            welcomeLabel,
            booksBtn, 
            customersBtn, 
            logoutBtn
        );
        
        // Set layout properties
        this.setSpacing(15); // Space between elements
        this.setAlignment(Pos.CENTER); // Center align elements
        this.setPrefSize(400, 300); // Set default size of the screen
    }

    public void show() {
        // Create and set up the scene
        Scene scene = new Scene(this, 400, 300); 
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bookstore App"); // Set window title
        primaryStage.show(); // Show the stage
    }
}
