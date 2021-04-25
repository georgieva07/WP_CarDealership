package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;

import java.util.List;

public interface ManufacturerService {
    Manufacturer findById(Long id) throws InvalidManufacturerIdException;

    List<Manufacturer> listAll();

    Manufacturer create(String name, Long countryId, String image) throws InvalidCountryIdException;

    Manufacturer update(Long id, String name, Long countryId, String image) throws InvalidCountryIdException, InvalidManufacturerIdException;

    Manufacturer delete(Long id) throws InvalidManufacturerIdException;

    CarModel removeCarModelFromManufacturer(Long manufacturerId, CarModel carModel) throws InvalidManufacturerIdException;

    CarModel addCarModelToManufacturer(Long manufacturerId, CarModel carModel) throws InvalidManufacturerIdException;

}
