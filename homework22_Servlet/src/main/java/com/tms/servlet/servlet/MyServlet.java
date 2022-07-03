package com.tms.servlet.servlet;

import com.tms.servlet.entity.Car;
import com.tms.servlet.entity.CarService;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/cars")
public class MyServlet extends HttpServlet {
    CarService carService = new CarService(new HashMap<String, Car>());

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet was created");
        Car car1 = new Car("VW", "silver", 5);
        Car car2 = new Car("Renault", "green", 6);
        Car car3 = new Car("AUDI", "white", 7);
        carService.addCar("1", car1);
        carService.addCar("2", car2);
        carService.addCar("3", car3);
    }

    //печать машины по id или список всех машин
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //установка интервала неактивности, после которого удаляются данные сессии
        //(это для более раннего вызова SessionListener, а то по умолчанию 30 минут многовато))
        req.getSession().setMaxInactiveInterval(20);

        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String cookie = req.getParameter("cookie");
            if (id != null) {
                if (id.equalsIgnoreCase("getAll")) {
                    carService.getAllCars(writer);
                } else {
                    carService.getCarWithID(writer, id);
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
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String model = req.getParameter("model");
            String color = req.getParameter("color");
            String age = req.getParameter("age");
            int ageFromReq = Integer.parseInt(age);
            Car carFromReq = new Car(model, color, ageFromReq);
            carService.getCars().put(id, carFromReq);
            for (Car car : carService.getCars().values()) {
                writer.println(car);
            }
        }
    }

    //Обновить данные существующей машины
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String model = req.getParameter("model");
            String color = req.getParameter("color");
            String age = req.getParameter("age");
            Collection<String> keys = carService.getCars().keySet();
            for (String key : keys) {
                if (key.equals(id)) {
                    if (model != null) {
                        carService.getCars().get(id).setModel(model);
                    }
                    if (color != null) {
                        carService.getCars().get(id).setColor(color);
                    }
                    if (age != null) {
                        carService.getCars().get(id).setAge(Integer.parseInt(age));
                    }
                }
            }
            for (Car car : carService.getCars().values()) {
                writer.println(car);
            }
        }
    }

    //Удалить машину
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            if (id != null) {
                Collection<String> keys = carService.getCars().keySet();
                for (String key : keys) {
                    if (key.equals(id)) {
                        carService.getCars().remove(id);
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