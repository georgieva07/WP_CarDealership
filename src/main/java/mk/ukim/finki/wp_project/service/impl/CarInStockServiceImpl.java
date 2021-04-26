package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarInStock;
import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarInStockIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.repository.CarInStockRepository;
import mk.ukim.finki.wp_project.service.CarInStockService;
import mk.ukim.finki.wp_project.service.CarService;
import mk.ukim.finki.wp_project.service.SalonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarInStockServiceImpl implements CarInStockService {
    private final CarInStockRepository carInStockRepository;
    private final CarService carService;
    private final SalonService salonService;

    public CarInStockServiceImpl(CarInStockRepository carInStockRepository, CarService carService, SalonService salonService) {
        this.carInStockRepository = carInStockRepository;
        this.carService = carService;
        this.salonService = salonService;
    }

    @Override
    public CarInStock findById(Long id) throws InvalidCarInStockIdException {
        return this.carInStockRepository.findById(id).orElseThrow(InvalidCarInStockIdException::new);
    }

    @Override
    public List<CarInStock> listAll() {
        return carInStockRepository.findAll();
    }

    @Override
    public CarInStock create(Long carId, Long salonId, Integer quantity) throws InvalidCarIdException, InvalidSalonIdException {
        Car car = this.carService.findById(carId);
        Salon salon = this.salonService.findById(salonId);
        CarInStock carInStock = new CarInStock(car, salon, quantity);
        return this.carInStockRepository.save(carInStock);
    }

    @Override
    public CarInStock update(Long id, Long carId, Long salonId, Integer quantity) throws InvalidSalonIdException, InvalidCarIdException, InvalidCarInStockIdException {
        CarInStock carInStock = this.findById(id);
        Car car = this.carService.findById(carId);
        Salon salon = this.salonService.findById(salonId);
        carInStock.setCar(car);
        carInStock.setSalon(salon);
        carInStock.setQuantity(quantity);
        return this.carInStockRepository.save(carInStock);
    }

    @Override
    public CarInStock delete(Long id) throws InvalidCarInStockIdException {
        CarInStock carInStock = this.findById(id);
        this.carInStockRepository.delete(carInStock);
        return carInStock;
    }
}
