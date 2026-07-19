package service;
import java.util.ArrayList;

import exception.BookAlreadyExistsException;
import exception.BookNotFoundException;
import exception.BookOperationException;
import model.Book;

public class LibraryService {
    private ArrayList<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
    }

    private boolean bookExists(long bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return true;
            }
        }
        return false;
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if(bookExists(book.getBookId())) {
            throw new BookAlreadyExistsException("Book with ID " + book.getBookId() + " already exists.");
        }
        books.add(book);
    }

    public void removeBook(long bookId) throws BookNotFoundException {
        Book book = findBookById(bookId);
            books.remove(book);    
        }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books available in the library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public Book findBookById(long bookId) throws BookNotFoundException {

        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }

        throw new BookNotFoundException("Book with ID " + bookId + " not found.");
    }

    public void borrowBook(long bookId) throws BookNotFoundException, BookOperationException {
        Book book = findBookById(bookId);
            book.borrowBook();
    }

    public void returnBook(long bookId) throws BookNotFoundException, BookOperationException {
        Book book = findBookById(bookId);
            book.returnBook();
    }

}
