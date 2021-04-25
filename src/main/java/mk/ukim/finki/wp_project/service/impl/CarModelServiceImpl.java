package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp_project.repository.CarModelRepository;
import mk.ukim.finki.wp_project.service.CarModelService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;
    private final ManufacturerService manufacturerService;

    public CarModelServiceImpl(CarModelRepository carModelRepository, ManufacturerService manufacturerService) {
        this.carModelRepository = carModelRepository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public CarModel findById(Long id) throws InvalidCarModelIdException {
        return this.carModelRepository.findById(id).orElseThrow(InvalidCarModelIdException::new);
    }

    @Override
    public List<CarModel> listAll() {
        return this.carModelRepository.findAll();
    }

    @Override
    public CarModel create(String name, Long manufacturerId, String image) throws InvalidManufacturerIdException {
        Manufacturer manufacturer = this.manufacturerService.findById(manufacturerId);
        CarModel carModel = new CarModel(name, manufacturer, image);
        this.manufacturerService.addCarModelToManufacturer(manufacturerId, carModel);
        return this.carModelRepository.save(carModel);
    }

    @Override
    public CarModel update(Long id, Long manufacturerId, String name, String image) throws InvalidCarModelIdException, InvalidManufacturerIdException {
        CarModel carModel = this.findById(id);
        this.manufacturerService.removeCarModelFromManufacturer(carModel.getManufacturer().getId(), carModel);
        this.manufacturerService.addCarModelToManufacturer(manufacturerId, carModel);
        Manufacturer manufacturer = this.manufacturerService.findById(manufacturerId);
        carModel.setManufacturer(manufacturer);
        carModel.setName(name);
        carModel.setImage(image);
        return this.carModelRepository.save(carModel);

    }

    @Override
    public CarModel delete(Long id) throws InvalidCarModelIdException {
        CarModel carModel = this.findById(id);
        this.carModelRepository.delete(carModel);
        return carModel;
    }

    @Override
    public Car removeCarFromCarModel(Long carModelId, Car car) throws InvalidCarModelIdException {
        CarModel carModel = this.findById(carModelId);
        carModel.getCars().remove(car);
        return car;
    }

    @Override
    public Car addCarToCarModel(Long carModelId, Car car) throws InvalidCarModelIdException {
        CarModel carModel = this.findById(carModelId);
        carModel.getCars().add(car);
        return car;
    }
}
