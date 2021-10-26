package com.zk.repository.impl;

import com.zk.entity.Admin;
import com.zk.repository.AdminRepository;
import com.zk.service.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    public Admin login(String username,String password){
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from admin where username=? and password=?";
        PreparedStatement statement = null;
        ResultSet resultSet =null;
        Admin admin =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                admin = new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return admin;
    }
}
