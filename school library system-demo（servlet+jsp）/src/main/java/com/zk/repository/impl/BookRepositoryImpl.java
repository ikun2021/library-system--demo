package com.zk.repository.impl;

import com.zk.entity.Book;
import com.zk.entity.Bookcase;
import com.zk.repository.BookRepository;
import com.zk.service.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll(int index, int LIMIT) {
        //can't use dbutils for query multiple stables
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid=bookcase.id limit ?,?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,index);
            statement.setInt(2,LIMIT);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                //只用堆内存，节省栈内存
                list.add(new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),new Bookcase(resultSet.getInt(6), resultSet.getString(7))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;

    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from book,bookcase where book.bookcaseid=bookcase.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count=0;
        try {
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return count;
    }
}
