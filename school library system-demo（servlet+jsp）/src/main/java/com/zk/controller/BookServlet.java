package com.zk.controller;

import com.zk.entity.Book;
import com.zk.entity.Borrow;
import com.zk.entity.Reader;
import com.zk.service.BookService;
import com.zk.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method==null){
            method="findall";
        }
        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        Integer readerid = reader.getId();
        switch(method){
            case"findall":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list =bookService.findAll(page);
                int i=0;
                req.setAttribute("list",list);
                req.setAttribute("dataPerpage",4);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("reader.jsp").forward(req,resp);
                break;
            case"borrow":
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                bookService.addBorrow(bookid,readerid);
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
                //show all books this reader borrow
            case"findAllBorrow":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                List<Borrow> borrowList=bookService.findByReaderId(readerid,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPerpage",4);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPages(readerid));
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);
                break;
        }




    }
}
