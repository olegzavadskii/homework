package com.tms.servlet;

import com.tms.dao.DBUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("LoginServlet was created");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUser dbUser = DBUser.getDbUser();
        String pathForAdmin = "registration.html";
        String pathForUser = "Cat.jpg";
        RequestDispatcher requestDispatcher;
        String loginForCheck = req.getParameter("loginForCheck");
        String passwordForCheck = req.getParameter("passwordForCheck");
        if (dbUser.checkWithRole(loginForCheck, passwordForCheck)) {
            requestDispatcher = req.getRequestDispatcher(pathForAdmin);
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = req.getRequestDispatcher(pathForUser);
            requestDispatcher.forward(req, resp);
        }
    }

}
