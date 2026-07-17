import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        if (book != null) {
            books.remove(book);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
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

    public Book findBookById(long bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null; // Book not found
    }

    public void borrowBook(long bookId) {
        Book book = findBookById(bookId);
        if (book != null && book.isAvailable()) {
            book.borrowBook();
            System.out.println("You have borrowed: " + book.getTitle());
        } else if (book != null) {
            System.out.println("Sorry, the book is currently not available.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(long bookId) {
        Book book = findBookById(bookId);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("You have returned: " + book.getTitle());
        } else if (book != null) {
            System.out.println("This book was not borrowed.");
        } else {
            System.out.println("Book not found.");
        }
    }

}
