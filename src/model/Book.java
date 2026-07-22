package model;

import exception.BookOperationException;

public class Book {

    private String title;
    private String author;
    private long bookId;
    private int totalQuantity;
    private int availableQuantity;

    public Book(String title, String author, long bookId, int totalQuantity) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank");
        }

        if (bookId <= 0 ) {
            throw new IllegalArgumentException("Book ID must be a positive number and cannot be null");
        }

        if (totalQuantity < 0) {
            throw new IllegalArgumentException("Total quantity cannot be negative");
        }

        if (availableQuantity < 0 || availableQuantity > totalQuantity) {
            throw new IllegalArgumentException("Available quantity must be between 0 and total quantity");
        }

        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity; // Initialize availableQuantity to totalQuantity
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    public long getAvailableQuantity(){
        return availableQuantity;
    }

    public long getTotalQuantity(){
        return totalQuantity;
    }

    public void borrowBook() throws BookOperationException {

        if (availableQuantity <= 0) {
            throw new BookOperationException("No copies available.");
        }
        availableQuantity--;
    }

    public void returnBook() throws BookOperationException {
        if (availableQuantity >= totalQuantity) {
            throw new BookOperationException("ALL COPIES ARE ALREADY RETURNED");
        }
        availableQuantity++;
    }

    @Override
    public String toString() {
        return "----------------------------\n"
                +"Book {\n" +
                "bookId=" + bookId +
                ", \ntitle='" + title + '\'' +
                ", \nauthor='" + author + '\'' +
                ", \ntotal=" + totalQuantity +
                ", \navailable=" + availableQuantity +
                "\n}\n" +
                "----------------------------";
    }
}