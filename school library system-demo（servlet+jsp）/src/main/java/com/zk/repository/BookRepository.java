package com.zk.repository;

import com.zk.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index,int LIMIT);
    public int count();

}
