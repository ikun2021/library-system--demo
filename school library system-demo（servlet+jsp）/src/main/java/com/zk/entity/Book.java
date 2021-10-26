package com.zk.entity;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private Bookcase bookcase;

    public Book(Integer id, String name, String author, Bookcase bookcase) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.bookcase = bookcase;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Bookcase getBookcase() {
        return bookcase;
    }

    public void setBookcase(Bookcase bookcase) {
        this.bookcase = bookcase;
    }
}
