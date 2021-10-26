package com.zk.service.impl;

import com.zk.entity.Book;
import com.zk.entity.Borrow;
import com.zk.repository.BookRepository;
import com.zk.repository.BorrowRepository;
import com.zk.repository.impl.BookRepositoryImpl;
import com.zk.repository.impl.BorrowRepositoryImpl;
import com.zk.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();
    private final int LIMIT=4;
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    @Override
    public List<Book> findAll(Integer page) {
        int index= (page-1)*LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count = bookRepository.count();
        int pages;
        if(count%LIMIT==0){
           pages=count/LIMIT;
        }else{
            pages=count/LIMIT+1;
        }
        return pages;
    }

    @Override
    public void addBorrow(Integer bookid,Integer readerid) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+14);
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);

        
    }

    @Override
    public List<Borrow> findByReaderId(Integer readerid, Integer page) {
        int index= (page-1)*LIMIT;
        return borrowRepository.findByReaderId(readerid,index,LIMIT);
    }

    @Override
    public int getBorrowPages(Integer readerid) {
        int count = borrowRepository.count(readerid);
        int pages;
        if(count%LIMIT==0){
            pages=count/LIMIT;
        }else{
            pages=count/LIMIT+1;
        }
        return pages;
    }

    @Override
    public List<Borrow> findByState(Integer state,Integer page) {
        int index= (page-1)*LIMIT;
        return borrowRepository.findByState(state,index,LIMIT);
    }

    @Override
    public int getPagesByState(Integer state) {
        int count = borrowRepository.countByState(state);
        int pages;
        if(count%LIMIT==0){
            pages=count/LIMIT;
        }else{
            pages=count/LIMIT+1;
        }
        return pages;
    }

    @Override
    public void handleBorrow(Integer id, Integer state, Integer adminId) {
        borrowRepository.handleBorrow(id,state,adminId);
    }
}
