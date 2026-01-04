package bookstoreapp;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import coe528projectbackend.Book; // Imports the Book class from the model package
import coe528projectbackend.Customer; // Imports the Customer class from the model package
import coe528projectbackend.BookStore;
import coe528projectbackend.GoldStatus;
import coe528projectbackend.Owner;
import coe528projectbackend.User;
import coe528projectbackend.customerStatus;
import coe528projectbackend.silverStatus;

// Utility class for switching scenes
public class SceneSwitcher {
    
    // Method to switch to a new scene
    public static void switchTo(Stage stage, Parent newSceneRoot) {
        // Create a new scene with the given root node
        Scene newScene = new Scene(newSceneRoot);
        
        // Set the new scene on the stage
        stage.setScene(newScene);
        
        // Show the stage if it's not already showing
        stage.show();
    }
}
