package app;

import java.util.Scanner;


import exception.*;
import model.*;
import service.*;
public class LibraryApp {
    public static void displayMainMenu(){
        System.out.println("\n Welcome toLibrary Management System!");
        System.out.println(" 1. Member Service\n 2. BookService\n 3. Exit");
    }
    public static void displayMenu(int choice) {
    
        if(choice == 1){
            System.out.println("Member Service");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Display Members");
            System.out.println("4. Find Members");
            System.out.println("5. Back to Main Menu");

        } else if(choice == 2) {
            System.out.println("Book Services");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display Borrow Record");
            System.out.println("7. Find Book by ID");
            System.out.println("8. Find Borrow Record");
            System.out.println("9. Back to Main Menu");

        } 
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService library = new BookService();
        MemberService memberService = new MemberService();
        BorrowAndReturnService bar = new BorrowAndReturnService(library, memberService);
        displayMainMenu();
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        while(true) {      
            
            if(choice == 1){
                displayMenu(choice);
                System.out.println("Enter Which Operation to perform: ");
                int choice2 = sc.nextInt();
                switch(choice2){
                    case 1:
                        System.out.println("Enter Member Id:");
                        long memberId = sc.nextLong();
                        sc.nextLine();
                        System.out.println("Enter Member Name:");
                        String memberName = sc.nextLine();
                        System.out.println("Enter Email id:");
                        String memberEmail = sc.nextLine();
                        System.out.println("Enter mobile number: ");
                        String memberPhone = sc.nextLine();
                        try {
                            Member mem = new Member(memberId, memberName, memberEmail, memberPhone);
                            memberService.addMember(mem);
                        } catch (IllegalArgumentException | MemberAlreadyExistException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter Member ID: ");
                        long memId = sc.nextLong();
                        try {
                            memberService.removeMember(memId);
                        } catch (MemberNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        memberService.displayMembers();
                        break;
                    case 4:
                        System.out.println("Enter Member ID: ");
                        long membId = sc.nextLong();
                        try {
                            Member mem1 = memberService.findMemberById(membId);
                            System.out.println(mem1);
                        } catch (MemberNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        displayMainMenu();
                        choice = sc.nextInt();
                        break;
                }
            }else if(choice == 2){
                displayMenu(choice);
                System.out.println("Enter Which Operation to perform: ");
                int choice2 = sc.nextInt();
                sc.nextLine();
                switch (choice2) { 
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter book author: ");
                        String author = sc.nextLine();
                        System.out.print("Enter book ID: ");
                        long bookId = sc.nextLong();
                        System.out.println("Enter the Total Quantity: ");
                        int totalQuantity = sc.nextInt();
                        try  {
                            Book newBook = new Book(title, author, bookId, totalQuantity);
                            library.addBook(newBook);
                            System.out.println("Book added successfully.");
                        } catch (IllegalArgumentException | BookAlreadyExistsException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Enter book ID to remove: ");
                        long removeBookId = sc.nextLong();
                        try{
                            library.removeBook(removeBookId);
                        } catch(BookNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        library.displayBooks();
                        break;
                    case 4:
                        System.out.println("Enter Member ID: ");
                        long memberId = sc.nextLong();
                        System.out.print("Enter book ID to borrow: ");
                        long borrowbookId = sc.nextLong();
                        BorrowRecord borrow = new BorrowRecord(memberId, borrowbookId);
                        try {
                            bar.borrowBook(borrow);
                            System.out.println("Book Borrowed Successfully!");
                        } catch (BookNotFoundException | BookOperationException | BookAlreadyBorrowedException | MemberNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Enter Member ID: ");
                        long returnMemberId = sc.nextLong();
                        System.out.print("Enter book ID to return: ");
                        long returnBookId = sc.nextLong();
                        try {
                            bar.returnBooks(returnMemberId, returnBookId);
                            System.out.println("Book returned Successfully!");
                        } catch (BorrowRecordIsNotFound | BookOperationException | BookNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        bar.displayBorrowRecord();
                        break;
                    case 7:
                        System.out.println("Enter Book Id to search: ");
                        long findbookId = sc.nextLong();
                        try {
                            Book book1 = library.findBookById(findbookId);
                            System.out.println(book1);
                        } catch (BookNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 8:
                        System.out.println("Enter Member Id: ");
                        long borrowMemberId = sc.nextLong();
                        System.out.println("Enter Book Id: ");
                        long borrowBookId = sc.nextLong();
                        try {
                            BorrowRecord br = bar.findBorrowRecord(borrowMemberId, borrowBookId);
                            System.out.println(br);
                        } catch (BorrowRecordIsNotFound e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 9:
                        displayMainMenu();
                        choice = sc.nextInt();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } else if(choice == 3){
                System.out.println("Exiting the Library Management System. Goodbye!");
                sc.close();
                return;
            } else {
                System.out.println("Invalid Input! try again");
                displayMainMenu();
                choice = sc.nextInt();
            }
            //sc.nextLine(); 
        }
    }
}