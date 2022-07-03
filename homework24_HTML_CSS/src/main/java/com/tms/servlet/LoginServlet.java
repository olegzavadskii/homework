package com.tms.servlet;

import com.tms.Gender;
import com.tms.entity.DBUser;
import com.tms.Role;
import com.tms.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    static DBUser dbUser = new DBUser(new ArrayList<User>());

    public static DBUser returnDBuser() {
        return dbUser;
    }

    @Override
    public void init() throws ServletException {
        dbUser.addUser(new User("admin1", "123", Gender.MALE, "I am an admin", Role.ADMIN));
        dbUser.addUser(new User("user1", "321", Gender.FEMALE, "I am a user", Role.USER));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathForAdmin = "registration_page.html";
        String pathForUser = "Cat.jpg";
        RequestDispatcher requestDispatcher;
        String loginForCheck = req.getParameter("loginForCheck");
        String passwordForCheck = req.getParameter("passwordForCheck");
        if (dbUser.checkWithRole(loginForCheck, passwordForCheck) > 0) {
            requestDispatcher = req.getRequestDispatcher(pathForAdmin);
            requestDispatcher.forward(req, resp);
        } else {
            requestDispatcher = req.getRequestDispatcher(pathForUser);
            requestDispatcher.forward(req, resp);
        }
    }

}
