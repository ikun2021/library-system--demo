package com.zk.controller;

import com.zk.entity.Admin;
import com.zk.entity.Book;
import com.zk.entity.Reader;
import com.zk.service.BookService;
import com.zk.service.LoginService;
import com.zk.service.impl.BookServiceImpl;
import com.zk.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        Object object = loginService.login(username,password,type);

        if(object!=null){
            HttpSession session = req.getSession();
            switch(type){
                case"reader":
                    Reader reader =(Reader) object;
                    session.setAttribute("reader",reader);
                    List<Book> list = bookService.findAll(1);
                    resp.sendRedirect("/book?page=1");
                    break;
                case"admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin",admin);
                    int a =0;
                    resp.sendRedirect("/admin?page=1");
                    break;
            }

            System.out.println("login successfully");
        }else{
            resp.sendRedirect("login.jsp");
        }


    }
}
