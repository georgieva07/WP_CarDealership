package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;

import java.util.List;

public interface CarModelService {
    CarModel findById(Long id) throws InvalidCarModelIdException;

    List<CarModel> listAll();

    CarModel create(String name, Long manufacturerId, String image) throws InvalidManufacturerIdException;
}
