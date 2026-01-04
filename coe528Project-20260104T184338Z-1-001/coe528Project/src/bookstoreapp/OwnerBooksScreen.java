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
import javafx.scene.control.*; // Imports UI controls like Label, TextField, and Button
import javafx.scene.layout.*; // Imports layout managers like BorderPane, VBox, and HBox
import javafx.stage.Stage; // Imports Stage for window handling
import javafx.scene.Scene; // Imports Scene to display UI components
import javafx.collections.FXCollections; // Imports FXCollections for observable lists
import javafx.scene.control.cell.PropertyValueFactory; // Imports PropertyValueFactory for table column bindings
import javafx.geometry.Pos; // Imports Pos for alignment settings
import javafx.geometry.Insets; // Imports Insets for padding and spacing
import java.io.IOException; // Imports IOException for handling file-related exceptions

// Defines the OwnerBooksScreen class, extending BorderPane for layout management
public class OwnerBooksScreen extends BorderPane {
    private TableView<Book> booksTable = new TableView<>(); // Creates a TableView to display books
    private Stage primaryStage; // Stores the main application window

    // Constructor to initialize the owner book management screen
    public OwnerBooksScreen(Stage primaryStage) {
        this.primaryStage = primaryStage; // Assigns the passed stage to the class field
        
        // Creates table columns for book name and price
        TableColumn<Book, String> nameCol = new TableColumn<>("Book Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);

        TableColumn<Book, Double> priceCol = new TableColumn<>("Book Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(100);

        // Adds columns to the booksTable
        booksTable.getColumns().addAll(nameCol, priceCol);
        
        // Populates the table with sample books
        booksTable.setItems(FXCollections.observableArrayList(
            new Book("Java101", 99.99),
            new Book("Harry Potter", 7.49),
            new Book("Finding Nemo", 24.48)
        ));
        
        booksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Adjusts column resizing policy

        // Creates input fields and labels for book details
        Label nameLabel = new Label("Book Name:");
        TextField nameField = new TextField();

        Label priceLabel = new Label("Book Price:");
        TextField priceField = new TextField();

        // Creates an Add button to add books to the table
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());

                if (!name.isEmpty()) {
                    booksTable.getItems().add(new Book(name, price)); // Adds new book to the table
                    try {
                        FileHandler.saveBooks(booksTable.getItems()); // Saves books to file
                        nameField.clear(); // Clears input fields after adding
                        priceField.clear();
                    } catch (IOException ex) {
                        showAlert("Save Error", "Failed to save books: " + ex.getMessage());
                    }
                } else {
                    showAlert("Input Error", "Book name cannot be empty");
                }
            } catch (NumberFormatException ex) {
                showAlert("Input Error", "Enter a valid price");
            } catch (Exception ex) {
                showAlert("Error", "Unknown error");
            }
        });

        // Creates layout for input fields
        VBox nameBox = new VBox(5, nameLabel, nameField);
        VBox priceBox = new VBox(5, priceLabel, priceField);
        HBox inputSection = new HBox(10, nameBox, priceBox, addButton);
        inputSection.setAlignment(Pos.CENTER);

        // Creates a Delete button to remove selected book
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Book selected = booksTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                booksTable.getItems().remove(selected); // Removes selected book
                try {
                    FileHandler.saveBooks(booksTable.getItems()); // Saves updated book list
                } catch (IOException ex) {
                    showAlert("Save Error", "Failed to save books: " + ex.getMessage());
                }
            } else {
                showAlert("Selection Error", "You must select a book");
            }
        });

        // Creates a Back button to return to the owner start screen
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new OwnerStartScreen(primaryStage).show());

        // Creates a layout for bottom buttons
        HBox bottomButtons = new HBox(10, addButton, deleteButton, backButton);
        bottomButtons.setAlignment(Pos.CENTER);

        // Arranges the UI elements in a VBox layout
        VBox layout = new VBox(15, booksTable, inputSection, bottomButtons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        this.setCenter(layout); // Sets the layout in the center of the BorderPane
    }

    // Displays an alert dialog with the given title and message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to display the OwnerBooksScreen
    public void show() {
        Scene scene = new Scene(this, 600, 400); // Creates a scene with dimensions 600x400
        primaryStage.setScene(scene); // Sets the scene for the primary stage
        primaryStage.setTitle("Bookstore App"); // Sets the window title
        primaryStage.show(); // Displays the stage
    }
}
