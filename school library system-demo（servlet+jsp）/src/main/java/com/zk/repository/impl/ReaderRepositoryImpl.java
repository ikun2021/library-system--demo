package com.zk.repository.impl;

import com.zk.entity.Reader;
import com.zk.repository.ReaderRepository;
import com.zk.service.JDBCTools;

import java.sql.*;

public class ReaderRepositoryImpl implements ReaderRepository {
    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username=? and password=?";
        PreparedStatement statement = null;
        ResultSet resultSet =null;
        Reader reader =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                reader = new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4
                ),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return reader;

    }
}
