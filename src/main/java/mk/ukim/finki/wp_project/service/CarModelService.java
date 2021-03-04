package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;

import java.util.List;

public interface CarModelService {
    CarModel findById(Long id) throws InvalidCarModelIdException;

    List<CarModel> listAll();

    CarModel create(String name, Long manufacturerId, String image) throws InvalidManufacturerIdException;

    CarModel update(Long id, Long manufacturerId, String name, String image) throws InvalidCarModelIdException, InvalidManufacturerIdException;

    CarModel delete(Long id) throws InvalidCarModelIdException;

    Car removeCarFromCarModel(Long carModelId, Car car) throws InvalidCarModelIdException;

    Car addCarToCarModel(Long carModelId, Car car) throws InvalidCarModelIdException;
}
