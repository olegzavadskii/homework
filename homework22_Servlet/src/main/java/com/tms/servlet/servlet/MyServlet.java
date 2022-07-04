package com.tms.servlet.servlet;

import com.tms.servlet.entity.Car;
import com.tms.servlet.dao.DBCar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@WebServlet(urlPatterns = "/cars")
public class MyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet was created");
    }

    //печать машины по id или список всех машин
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //установка интервала неактивности, после которого удаляются данные сессии
        //(это для более раннего вызова SessionListener, а то по умолчанию 30 минут многовато))
        req.getSession().setMaxInactiveInterval(20);
        DBCar dbCar = DBCar.getDbCar();

        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String cookie = req.getParameter("cookie");
            if (id != null) {
                if (id.equalsIgnoreCase("getAll")) {
                    dbCar.getAllCars(writer);
                } else {
                    dbCar.getCarWithID(writer, id);
                }
            } else if (cookie != null) {
                //вернуть время доступа в куках не получается
                HttpSession session = req.getSession();
                Cookie cookie1 = new Cookie("myCookie", "value");
                resp.addCookie(cookie1);
                Date date = new Date(session.getLastAccessedTime());
                DateFormat formatToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String s = formatToDate.format(date);
                writer.println(s);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher
                        ("/hello_world.html");
                requestDispatcher.forward(req, resp);
            }
        }
    }

    //сохранить данные о новой машине
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBCar dbCar = DBCar.getDbCar();
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String model = req.getParameter("model");
            String color = req.getParameter("color");
            String age = req.getParameter("age");
            int ageFromReq = Integer.parseInt(age);
            Car carFromReq = new Car(model, color, ageFromReq);
            dbCar.getCars().put(id, carFromReq);
            dbCar.getAllCars(writer);
        }
    }

    //Обновить данные существующей машины
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBCar dbCar = DBCar.getDbCar();
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String model = req.getParameter("model");
            String color = req.getParameter("color");
            String age = req.getParameter("age");
            Collection<String> keys = dbCar.getCars().keySet();
            for (String key : keys) {
                if (key.equals(id)) {
                    if (model != null) {
                        dbCar.getCars().get(id).setModel(model);
                    }
                    if (color != null) {
                        dbCar.getCars().get(id).setColor(color);
                    }
                    if (age != null) {
                        dbCar.getCars().get(id).setAge(Integer.parseInt(age));
                    }
                }
            }
            for (Car car : dbCar.getCars().values()) {
                writer.println(car);
            }
        }
    }

    //Удалить машину
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBCar dbCar = DBCar.getDbCar();
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            if (id != null) {
                Collection<String> keys = dbCar.getCars().keySet();
                for (String key : keys) {
                    if (key.equals(id)) {
                        dbCar.getCars().remove(id);
                        writer.println("A car was deleted successfully");
                        break;
                    }
                }
            } else {
                writer.println("A car with this ID doesn't exist");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("Servlet was destroyed");
    }

}
