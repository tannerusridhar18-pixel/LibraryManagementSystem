public class Book {

    private String title;
    private String author;
    private long bookId;
    private boolean available;

    public Book(String title, String author, long bookId) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank");
        }

        if (bookId <= 0) {
            throw new IllegalArgumentException("Book ID must be a positive number");
        }

        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.available = true;
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
        return available;
    }

    @Override
    public String toString() {
        return "Book {" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}