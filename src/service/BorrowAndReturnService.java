package service;

import exception.*;
import model.Book;
import model.BorrowRecord;

import java.util.HashMap;

public class BorrowAndReturnService {

    private HashMap<String ,BorrowRecord> borrowRecord;
    private BookService bookService;
    private MemberService memberService;

    public BorrowAndReturnService(BookService bookService, MemberService memberService){
        borrowRecord = new HashMap<>();
        this.bookService = bookService;
        this.memberService = memberService;
    }

    

    public void borrowBook(BorrowRecord record) throws BookNotFoundException, BookOperationException, BookAlreadyBorrowedException, MemberNotFoundException{
        Book book = bookService.findBookById(record.getBookId());
        if(book == null){
            throw new BookNotFoundException("Book Not Found");
        }
        if(!book.isAvailable()){
            throw new BookOperationException("No copies of this book are currently available.");
        }
        if(!memberService.memberExists(record.getMemberId())){
            throw new MemberNotFoundException("No member found with the given Id");
        }
        String key = record.getMemberId() + "-" + record.getBookId();
        if(borrowRecord.containsKey(key)){
            throw new BookAlreadyBorrowedException("You have already borrowed this book");
        }
        book.borrowBook();
        borrowRecord.put(key, record);
    }    

    public void displayBorrowRecord(){
        for(BorrowRecord record : borrowRecord.values()){
            System.out.println(record);
        }
    }
    public BorrowRecord findBorrowRecord(long memberId, long bookId) throws BorrowRecordIsNotFound{
        String key = memberId + "-" + bookId;
        if(!(borrowRecord.containsKey(key))){
            throw new BorrowRecordIsNotFound("Record not found");
        }
        return borrowRecord.get(key);
    }

    public void returnBooks(long memberId, long bookId) throws BorrowRecordIsNotFound, BookOperationException, BookNotFoundException{
        String key = memberId + "-" + bookId;
        if(!(borrowRecord.containsKey(key))){
            throw new BorrowRecordIsNotFound("This book is not borrowed");
        }
        Book book = bookService.findBookById(bookId);

        if (book == null) {
            throw new BookNotFoundException("Book not found.");
        }

        book.returnBook();
        BorrowRecord record = borrowRecord.get(key);
        record.returnBook();
    }


}
