package com.tms.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyServlet extends HttpServlet {
    Map<String, Car> cars = new HashMap<>();

    @Override
    public void init() throws ServletException {
        Car car1 = new Car("VW", "silver", 5);
        Car car2 = new Car("Renault", "green", 6);
        Car car3 = new Car("AUDI", "white", 7);
        cars.put("1", car1);
        cars.put("2", car2);
        cars.put("3", car3);
        super.init();
    }

    //печать машины по id или список всех машин
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String id = req.getParameter("id");
            String cookie = req.getParameter("cookie");
            if (id != null) {
                Collection<String> keys = cars.keySet();
                for (String key : keys) {
                    if (key.equals(id)) {
                        writer.println(cars.get(id).toString());
                        break;
                    }
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
                for (Car car : cars.values()) {
                    writer.println(car);
                }
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
            cars.put(id, carFromReq);
            for (Car car : cars.values()) {
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
            Collection<String> keys = cars.keySet();
            for (String key : keys) {
                if (key.equals(id)) {
                    if (model != null) {
                        cars.get(id).setModel(model);
                    }
                    if (color != null) {
                        cars.get(id).setColor(color);
                    }
                    if (age != null) {
                        cars.get(id).setAge(Integer.parseInt(age));
                    }
                }
            }
            for (Car car : cars.values()) {
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
                Collection<String> keys = cars.keySet();
                for (String key : keys) {
                    if (key.equals(id)) {
                        cars.remove(id);
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
        super.destroy();
    }

}
