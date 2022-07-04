package com.tms.servlet.dao;

import com.tms.servlet.entity.Car;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DBCar {

    Car car1 = new Car("VW", "silver", 5);
    Car car2 = new Car("Renault", "green", 6);
    Car car3 = new Car("AUDI", "white", 7);
    Map<String, Car> cars = new HashMap<String, Car>();

    /*блок инициализации поля-мапы*/ {
        cars.put("1", car1);
        cars.put("2", car2);
        cars.put("3", car3);
    }

    private static DBCar dbCar;

    private DBCar() {
    }

    public static DBCar getDbCar() {
        if (dbCar != null) {
            return dbCar;
        } else {
            synchronized (DBCar.class) {
                if (dbCar == null) {
                    dbCar = new DBCar();
                }
            }
        }
        return dbCar;
    }

    public void addCar(String id, Car car) {
        cars.put(id, car);
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public void getAllCars(PrintWriter writer) {
        for (Car car : cars.values()) {
            writer.println(car);
        }
    }

    public void getCarWithID(PrintWriter writer, String id) {
        Collection<String> keys = cars.keySet();
        for (String key : keys) {
            if (key.equals(id)) {
                writer.println(cars.get(id).toString());
                break;
            }
        }
    }
}
