package com.zk.entity;

public class Borrow {
    private Integer id;
    private Book book;
    private Reader reader;
    private String borrowTime;
    private String returnTime;
    private Integer state;

    public Borrow(Integer id, Book book, Reader reader, String borrowTime, String returnTime, Integer state) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public Integer getState() {
        return state;
    }
}
