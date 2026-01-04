/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapp; // Defines the package for the class

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
import javafx.stage.Stage; // Imports Stage for window handling
import javafx.scene.Scene; // Imports Scene to display UI components
import javafx.geometry.Pos; // Imports Pos for alignment settings
import javafx.scene.text.Font; // Imports Font for text styling
import javafx.scene.text.FontWeight; // Imports FontWeight for bold text
import bookstoreapp.model.Customer; // Imports Customer class from the model package

// Defines the LoginScreen class, extending BorderPane for layout management
public class LoginScreen extends BorderPane {
    private Stage primaryStage; // Stores the main application window

    // Constructor that initializes the login screen
    public LoginScreen(Stage primaryStage) {
        this.primaryStage = primaryStage; // Assigns the passed stage to the class field
        
        // Creates a label for the title
        Label title = new Label("Welcome to the BookStore App");
        
        // Creates text fields for username and password input
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        
        // Creates a login button
        Button loginButton = new Button("Login");
        
        // Sets the font style for the title label
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Sets preferred width for username field
        usernameField.setPrefWidth(300);
        usernameField.setMinWidth(300);
        usernameField.setMaxWidth(300);

        // Sets preferred width for password field
        passwordField.setPrefWidth(300);
        passwordField.setMinWidth(300);
        passwordField.setMaxWidth(300);

        // Creates a VBox layout and adds UI components with spacing of 10 pixels
        VBox vbox = new VBox(10, title, 
            new Label("Username:"), usernameField,
            new Label("Password:"), passwordField, 
            loginButton);
        vbox.setAlignment(Pos.CENTER); // Centers the VBox content
        this.setCenter(vbox); // Sets the VBox to the center of the BorderPane

        // Defines login button action handler
        loginButton.setOnAction(e -> {
            // Checks if the entered username and password match admin credentials
            if (usernameField.getText().equals("admin") && 
                passwordField.getText().equals("admin")) {
                new OwnerStartScreen(primaryStage).show(); // Opens owner screen if admin
            } else {
                // Creates a new Customer object with 0 points
                Customer customer = new Customer(
                    usernameField.getText(), 
                    passwordField.getText(), 
                    0
                );
                new CustomerStartScreen(primaryStage, customer).show(); // Opens customer screen
            }
        });
    }

    // Method to display the login screen
    public void show() {
        Scene scene = new Scene(this, 400, 300); // Creates a scene with dimensions 400x300
        primaryStage.setScene(scene); // Sets the scene for the primary stage
        primaryStage.setTitle("Bookstore App"); // Sets the window title
        primaryStage.show(); // Displays the stage
    }
}
