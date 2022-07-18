package com.tms.servlet;

import com.tms.dao.BookDBStore;
import com.tms.entity.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/start", loadOnStartup = 1)
public class StartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        BookDBStore bookDBStore = BookDBStore.getBookDBStore();
        Book book1 = new Book("Stiven King", "The Dark Tower", 80.0);
        Book book2 = new Book("John Tolkien", "The Lord of the rings", 100.0);
        Book book3 = new Book("Joanne Rowling", "Harry Potter", 90.0);
        bookDBStore.addBook(book1);
        bookDBStore.addBook(book2);
        bookDBStore.addBook(book3);
    }
}
