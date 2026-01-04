/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapp; // Defines the package for this class

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
import javafx.application.Application; // Imports Application for JavaFX
import javafx.collections.FXCollections; // Imports FXCollections for observable lists
import javafx.stage.Stage; // Imports Stage for window handling
import java.io.IOException; // Imports IOException for handling file-related exceptions
import java.util.List; // Imports List for managing collections of objects

// Main class extending Application to start the JavaFX application
public class Main extends Application {
    
    // Overrides the start method to set up the application
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load existing book and customer data from files
            List<Book> loadedBooks = FileHandler.loadBooks();
            List<Customer> loadedCustomers = FileHandler.loadCustomers();
            
            // Initialize DataManager with loaded data wrapped in observable lists
            DataManager.init(
                FXCollections.observableArrayList(loadedBooks),
                FXCollections.observableArrayList(loadedCustomers)
            );
            
            // Create and display the login screen
            LoginScreen loginScreen = new LoginScreen(primaryStage);
            loginScreen.show();
            
            // Ensures data is saved when the application is closed
            primaryStage.setOnCloseRequest(event -> {
                try {
                    // Save books and customers data to files before exiting
                    FileHandler.saveBooks(DataManager.getBooks());
                    FileHandler.saveCustomers(DataManager.getCustomers());
                } catch (IOException e) {
                    System.err.println("Error saving data: " + e.getMessage()); // Print error if saving fails
                }
            });
            
        } catch (IOException e) {
            System.err.println("Error loading initial data: " + e.getMessage()); // Print error if loading fails
            
            // If data loading fails, initialize with default sample data
            DataManager.init(
                FXCollections.observableArrayList(
                    new Book("book1", 9.99) // Creates a sample book entry
                ),
                FXCollections.observableArrayList(
                    new Customer("user1", "password", 0) // Creates a sample customer entry
                )
            );
            
            new LoginScreen(primaryStage).show(); // Displays the login screen
        }
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args); // Calls launch to start the JavaFX application
    }
}
