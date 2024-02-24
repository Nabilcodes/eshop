package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarRetrieveService {
    public List<Car> findAll();
    public Car findById(String carId);
}
