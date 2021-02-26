package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;

import java.util.List;

public interface ManufacturerService {
    Manufacturer findById(Long id) throws InvalidManufacturerIdException;

    List<Manufacturer> listAll();

    Manufacturer create(String name, Long countryId) throws InvalidCountryIdException;
}
