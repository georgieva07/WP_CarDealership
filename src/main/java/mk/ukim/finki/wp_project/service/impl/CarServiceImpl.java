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
    public Car create(Long carModelId, String body, String engine, String turbo, int doors, String color, Double price, String image) throws InvalidCarModelIdException {
        CarModel carModel = this.carModelService.findById(carModelId);
        String name = carModel.getManufacturer().getName() + " " + carModel.getName() + " " + body + " - " + color;
        Car car = new Car(name, carModel, body, engine, turbo, doors, color, price, image);
        this.carModelService.addCarToCarModel(carModelId, car);
        return this.carRepository.save(car);
    }

    @Override
    public Car update(Long id, Long carModelId, String body, String engine, String turbo, Integer doors, String color, Double price, String image) throws InvalidCarModelIdException, InvalidCarIdException {
        Car car = this.findById(id);
        CarModel carModel = this.carModelService.findById(carModelId);
        this.carModelService.removeCarFromCarModel(car.getModel().getId(), car);
        this.carModelService.addCarToCarModel(carModelId, car);
        String name = carModel.getManufacturer().getName() + " " + carModel.getName() + " " + body + " - " + color;
        car.setName(name);
        car.setModel(carModel);
        car.setBody(body);
        car.setEngine(engine);
        car.setTurbo(turbo);
        car.setDoors(doors);
        car.setColor(color);
        car.setPrice(price);
        car.setImage(image);
        return this.carRepository.save(car);
    }

    @Override
    public Car delete(Long id) throws InvalidCarIdException {
        Car car = this.findById(id);
        this.carRepository.delete(car);
        return car;
    }
}
