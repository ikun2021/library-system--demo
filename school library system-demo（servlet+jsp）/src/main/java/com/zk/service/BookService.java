package com.zk.service;

import com.zk.entity.Book;
import com.zk.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(Integer page);

    public int getPages();

    public void addBorrow(Integer bookid,Integer readerid);

    public List<Borrow> findByReaderId(Integer readerid, Integer page);

    public int getBorrowPages(Integer readerid);

    public List<Borrow> findByState(Integer state,Integer page);

    public int getPagesByState(Integer state);

    public void handleBorrow(Integer id,Integer state,Integer adminId);


}
