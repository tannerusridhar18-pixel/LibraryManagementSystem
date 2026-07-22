package model;

import java.time.LocalDate;

public class BorrowRecord{

    private long memberId;
    private long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    enum BorrowStatus {
        BORROWED,
        RETURNED,
        LOST
    }

    private BorrowStatus status; 

    public BorrowRecord(long memberId, long bookId){
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
        status = BorrowStatus.BORROWED;
    }

    public long getMemberId(){
        return memberId;
    }

    public long getBookId(){
        return bookId;
    }

    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }

    public BorrowStatus getStatus(){
        return status;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.status = BorrowStatus.RETURNED;
    }

    @Override
    public String toString(){
        return "----------------------------\n" +
                "Borrow Record\n" +
                "MemberId:" + memberId +
                ", \nBookId:'" + bookId + '\'' +
                ", \nBorrowed Date:'" + borrowDate + '\'' +
                ", \nReturnDate:" + returnDate +
                ", \nStatus=" + status +
                "\n}\n" +
                "----------------------------";
    }

}