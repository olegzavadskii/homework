package com.tms.servlet;

import com.tms.Gender;
import com.tms.Role;
import com.tms.entity.DBUser;
import com.tms.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/registration")
public class ServletForRegistration extends HttpServlet {
    DBUser dbUser = LoginServlet.returnDBuser();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathIfError = "error_page.html";
        RequestDispatcher requestDispatcher;

        String loginForRegistration = req.getParameter("loginForRegistration");
        String passwordForRegistration = req.getParameter("passwordForRegistration");
        Gender genderForRegistration = Gender.valueOf(req.getParameter("genderForRegistration"));
        String descriptionForRegistration = req.getParameter("descriptionForRegistration");
        Role roleForRegistration = Role.valueOf(req.getParameter("roleForRegistration"));

        User userForRegistration = new User(loginForRegistration,
                passwordForRegistration, genderForRegistration,
                descriptionForRegistration, roleForRegistration);
        if (dbUser.checkWithLogin(userForRegistration)) {
            requestDispatcher = req.getRequestDispatcher(pathIfError);
            requestDispatcher.forward(req, resp);
        } else {
            dbUser.addUser(userForRegistration);
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("You saved a user successfully");
            }
        }
    }
}
