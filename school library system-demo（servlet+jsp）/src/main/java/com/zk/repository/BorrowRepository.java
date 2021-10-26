package com.zk.repository;

import com.zk.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerid,String borrowTime,String returnTime,Integer adminid,Integer state);
    public List<Borrow> findByReaderId(Integer readerid,Integer index,Integer LIMIT);
    public int count(Integer readerid);
    public List<Borrow> findByState(Integer state,Integer index,Integer LIMIT);
    public int countByState(Integer state);
    public void handleBorrow(Integer id, Integer state, Integer adminId);


}
