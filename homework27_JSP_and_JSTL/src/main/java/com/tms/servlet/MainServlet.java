package com.tms.servlet;

import com.tms.dao.BookDBStore;
import com.tms.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDBStore bookDBStore = BookDBStore.getBookDBStore();
        List<Book> books = bookDBStore.getList();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/show.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookUUID = req.getParameter("bookUUID");
        BookDBStore bookDBStore = BookDBStore.getBookDBStore();
        Book bookWithUUID = bookDBStore.findWithUUID(UUID.fromString(bookUUID));
        bookDBStore.removeBook(bookWithUUID);
    }
}
