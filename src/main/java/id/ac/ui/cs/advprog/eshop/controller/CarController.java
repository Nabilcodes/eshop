package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarCreateService;
import id.ac.ui.cs.advprog.eshop.service.CarDeleteService;
import id.ac.ui.cs.advprog.eshop.service.CarRetrieveService;
import id.ac.ui.cs.advprog.eshop.service.CarUpdateService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController extends ProductController {

    @Autowired
    private CarCreateService carCreateService;

    @Autowired
    private CarDeleteService carDeleteService;

    @Autowired
    private CarRetrieveService carRetrieveService;

    @Autowired
    private CarUpdateService carUpdateService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car",car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model){
        carCreateService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model){
        List<Car> allCars = carRetrieveService.findAll();
        model.addAttribute("cars",allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carRetrieveService.findById(carId);
        model.addAttribute("car",car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carUpdateService.update(car.getCarId(), car);

        return "redirect:listCar";
    }
}
