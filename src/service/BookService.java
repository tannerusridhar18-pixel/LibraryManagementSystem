package service;
import java.util.HashMap;

import exception.BookAlreadyExistsException;
import exception.BookNotFoundException;
import exception.BookOperationException;
import model.Book;

public class BookService {
    private HashMap<Long, Book> books;

    public BookService() {
        books = new HashMap<>();
    }

    private boolean bookExists(long bookId) {
        return books.containsKey(bookId);
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if(bookExists(book.getBookId())) {
            throw new BookAlreadyExistsException("Book with ID " + book.getBookId() + " already exists.");
        }
        
        books.put(book.getBookId(), book);
    }

    public void removeBook(long bookId) throws BookNotFoundException {
        Book book = findBookById(bookId);
            books.remove(book.getBookId());    
        }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books available in the library:");
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    public Book findBookById(long bookId) throws BookNotFoundException {

        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        return book;
    }

}
