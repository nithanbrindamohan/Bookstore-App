/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528projectbackend;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


/**
 *
 * @author chasv
 */
public class BookStore {
    private static BookStore instance;
    private ArrayList<Book> books;
    private ArrayList<Customer> customers;
    private File customerMemory;
    private File booksMemory;

    // Private constructor to enforce Singleton pattern
    private BookStore() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
        customerMemory = new File("customers.txt");
        booksMemory = new File("books.txt");
        
        // Ensure the files exist or are created
    try {
        if (!booksMemory.exists()) {
            booksMemory.createNewFile();
        }
        if (!customerMemory.exists()) {
            customerMemory.createNewFile();
        }
    } catch (IOException e) {
        System.out.println("An error occurred while creating the files: " + e.getMessage());
    }

        this.readBooksMemory();
        this.readCustomerMemory();
    }

    // Singleton method to get the only instance of BookStore
    public static BookStore getInstance() {
        if (instance == null) {
            instance = new BookStore();
        }
        return instance;
    }

    // Method to add a book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
        this.updateBooksMemory();
    }

    // Method to remove a book
    public void removeBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title)) {
                books.remove(i);
                System.out.println("Book removed: " + title);
                return;
            }
        }
        this.updateBooksMemory();
    }

    // Method to add a customer
    public void addCustomer(Customer customer) {
            customers.add(customer);
            System.out.println("Customer added: " + customer.getName());
            this.updateCustomerMemory();
    }

    // Method to remove a customer
    public void removeCustomer(String userName) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(userName)) {
                customers.remove(i);
                System.out.println("Customer removed: " + userName);
                return;
            }
        }
        this.updateCustomerMemory();
    }
    
    public void updateCustomerMemory() {
        try {
            if (customerMemory.exists()) {
                customerMemory.delete(); // Delete the file if it exists
            }
            customerMemory.createNewFile(); // Create a new empty file
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            FileWriter writer = new FileWriter("customers.txt",true);
            if(customers.size() > 0) {
                for (int i = 0; i < customers.size(); i++) {
                    writer.write(customers.get(i).getName()+",");
                    writer.write(customers.get(i).getPassWord()+",");
                    writer.write(customers.get(i).getPoints()+",");
                }
            }
            writer.close();
           
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
    }
    
    public void updateBooksMemory() {
    try {
            if (booksMemory.exists()) {
                booksMemory.delete(); // Delete the file if it exists
            }
            booksMemory.createNewFile(); // Create a new empty file
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            FileWriter writer = new FileWriter("books.txt",true);
            if(books.size() > 0) {
                for (int i = 0; i < books.size(); i++) {
                    writer.write(books.get(i).getTitle()+",");
                    writer.write(books.get(i).getAuthor()+",");
                    writer.write(books.get(i).getPrice()+",");
                }
            }
            writer.close();
           
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    
    public void readCustomerMemory() {
        try {
            //scanner used to read the file
            Scanner scanner = new Scanner(customerMemory);

            // causes scanner to break everytime "," is encountered
            scanner.useDelimiter(",");

            // Read tokens and set to array, will run until the end of file
            while (scanner.hasNext()) {
                String tokenName = scanner.next();
                String tokenPassword = scanner.next();
                String tokenPoints = scanner.next();
                int pointsVal = Integer.parseInt(tokenPoints);
                customers.add(new Customer(tokenName,tokenPassword,pointsVal));
            }

            // Close scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    
    public void readBooksMemory() {
        try {
            //scanner used to read the file
            Scanner scanner = new Scanner(booksMemory);

            // causes scanner to break everytime "," is encountered
            scanner.useDelimiter(",");

            // Read tokens and set to array, will run until the end of file
            while (scanner.hasNext()) {
                String tokenTitle = scanner.next();
                String tokenAuthor = scanner.next();
                String tokenPrice = scanner.next();
                int bookPrice = Integer.parseInt(tokenPrice);
                books.add(new Book(tokenTitle,tokenAuthor,bookPrice));
            }

            // Close scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    // Get the singleton instance of the BookStore
    BookStore bookStore = BookStore.getInstance();

    // Create and add customers to the bookstore
    Customer customer1 = new Customer("chash", "123p", 0);
    Customer customer2 = new Customer("bhash", "123dp", 10000);

    // Add customers to the bookstore
    bookStore.addCustomer(customer1);
    bookStore.addCustomer(customer2);

    // Update the customer memory file
    bookStore.updateCustomerMemory();

    // Confirmation message
    System.out.println("Customer memory has been successfully updated!");
    
}
    
}

