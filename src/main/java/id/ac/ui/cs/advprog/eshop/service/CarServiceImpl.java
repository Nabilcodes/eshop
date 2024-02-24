package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarCreateService,CarRetrieveService,CarUpdateService,CarDeleteService{

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        // TODO Autp-generated method stub
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId){
        Car car = carRepository.findById(carId);
        return car;
    }

    @Override
    public Car update(String carId, Car car) {
        //TODO Auto-generated method stub
        carRepository.update(carId, car);
        return null;
    }

    @Override
    public void deleteById(String carId) {
        //TODO Auto-generated method stub
        carRepository.deleteById(carId);
    }

}
