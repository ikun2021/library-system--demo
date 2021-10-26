package com.zk.repository.impl;

import com.zk.entity.Book;
import com.zk.entity.Borrow;
import com.zk.entity.Reader;
import com.zk.repository.BorrowRepository;
import com.zk.service.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowTime, String returnTime, Integer adminid, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql ="insert into borrow(bookid,readerid,borrowTime,returnTime,state) values(?,?,?,?,0)";
        PreparedStatement preparedStatement =null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookid);
            preparedStatement.setInt(2,readerid);
            preparedStatement.setString(3,borrowTime);
            preparedStatement.setString(4,returnTime);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                JDBCTools.release(connection,preparedStatement,null);
            }

        }
    }

    @Override
    public List<Borrow> findByReaderId(Integer readerid,Integer index,Integer LIMIT) {
        Connection connection = JDBCTools.getConnection();
        String sql ="select borrow.id,book.name,book.author,reader.name,reader.tel,borrowtime,returntime,state from book,borrow,reader where readerid=? and bookid=book.id and readerid=reader.id limit ?,?";

        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        List<Borrow> list = new ArrayList<>();
        Borrow borrow;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,readerid);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,LIMIT);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                borrow=new Borrow(resultSet.getInt(1),new Book(resultSet.getString(2),resultSet.getString(3)),new Reader(resultSet.getString(4),
                        resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8));
                list.add(borrow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                JDBCTools.release(connection,preparedStatement,null);
            }

        }
        System.out.println(list);
        return list;

    }

    @Override
    public int count(Integer readerid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from book,borrow,reader where readerid=? and bookid=book.id and readerid=reader.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count=0;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,readerid);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        System.out.println(count);
        int i=0;
        return count;

    }

    @Override
    public List<Borrow> findByState(Integer state,Integer index,Integer LIMIT) {
        Connection connection = JDBCTools.getConnection();
        String sql ="select borrow.id,book.name,book.author,reader.name,reader.tel,borrowtime,returntime,state from book,borrow,reader where state=? and bookid=book.id and readerid=reader.id limit ?,?";

        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        List<Borrow> list = new ArrayList<>();
        Borrow borrow;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,index);
            preparedStatement.setInt(3,LIMIT);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                borrow=new Borrow(resultSet.getInt(1),new Book(resultSet.getString(2),resultSet.getString(3)),new Reader(resultSet.getString(4),
                        resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8));
                list.add(borrow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                JDBCTools.release(connection,preparedStatement,resultSet);
            }

        }
        System.out.println(list);
        return list;
    }

    public int countByState(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from book,borrow,reader where state=? and bookid=book.id and readerid=reader.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, state);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection, statement, resultSet);
        }
        System.out.println(count);
        return count;
    }

    @Override
    public void handleBorrow(Integer id, Integer state, Integer adminId) {
        Connection connection = JDBCTools.getConnection();
        String sql ="update borrow set state=?,adminid=? where id=?";
        PreparedStatement preparedStatement =null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,adminId);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                JDBCTools.release(connection,preparedStatement,null);
            }

        }

    }
}
