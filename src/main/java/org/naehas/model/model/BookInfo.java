package org.naehas.model.model;

import java.util.Date;

public class BookInfo {
    private Book book;
    private Date borrowDate;

    public BookInfo(Book book, Date borrowDate) {
        this.book = book;
        this.borrowDate = borrowDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
}
