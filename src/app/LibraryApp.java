package app;
import java.util.Scanner;

import model.Book;
public class LibraryApp {
    public static void displayMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Display Books");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Find Book by ID");
        System.out.println("7. Exit");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        while(true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            switch (choice) { 
                
                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter book author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter book ID: ");
                    long bookId = sc.nextLong();
                    Book newBook = new Book(title, author, bookId);
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    long removeBookId = sc.nextLong();
                    //Book bookToRemove = library.findBookById(removeBookId);
                    library.removeBook(removeBookId);
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    System.out.print("Enter book ID to borrow: ");
                    long borrowBookId = sc.nextLong();
                    library.borrowBook(borrowBookId);
                    break;
                case 5:
                    System.out.print("Enter book ID to return: ");
                    long returnBookId = sc.nextLong();
                    library.returnBook(returnBookId);
                    break;
                case 6:
                    System.out.print("Enter book ID to find: ");
                    long findBookId = sc.nextLong();
                    Book foundBook = library.findBookById(findBookId);
                    if (foundBook != null) {
                        System.out.println("Found Book: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}