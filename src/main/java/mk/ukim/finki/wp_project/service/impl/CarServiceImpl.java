package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.repository.CarRepository;
import mk.ukim.finki.wp_project.service.CarModelService;
import mk.ukim.finki.wp_project.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarModelService carModelService;

    public CarServiceImpl(CarRepository carRepository, CarModelService carModelService) {
        this.carRepository = carRepository;
        this.carModelService = carModelService;
    }

    @Override
    public Car findById(Long id) throws InvalidCarIdException {
        return this.carRepository.findById(id).orElseThrow(InvalidCarIdException::new);
    }

    @Override
    public List<Car> listAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Car create(Long modelId, int doors, String engine, String body, String turbo, String color) throws InvalidCarModelIdException {
        CarModel model = this.carModelService.findById(modelId);
        Car car = new Car(doors, engine, body, turbo, color, model);
        return this.carRepository.save(car);
    }
}
