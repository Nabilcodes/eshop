package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

public class CarController extends ProductController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car",car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model){
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model){
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars",allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car",car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribiute Car car, Model model) {
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);

        return "redirect:listCar";
    }
}
