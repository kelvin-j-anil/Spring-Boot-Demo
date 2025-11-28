package com.example.demo;

public class Book {
    public int bookId;
    public String bookName;

    public Book(int bid, String bname){
        bookId = bid;
        bookName = bname;
    }

    public Book(int bid){
        bookId = bid;
    }

    public Book (){}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
