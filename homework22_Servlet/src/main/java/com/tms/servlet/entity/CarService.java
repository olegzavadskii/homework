package com.tms.servlet.entity;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

public class CarService {
    private Map<String, Car> cars;

    public CarService(Map<String, Car> cars) {
        this.cars = cars;
    }

    public Map<String, Car> addCar(String id, Car car) {
        cars.put(id, car);
        return cars;
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
