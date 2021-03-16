package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;

import java.util.List;

public interface CountryService {
    Country findById(Long id) throws InvalidCountryIdException;

    List<Country> listAll();

    Country create(String name);

    Country update(Long id, String name) throws InvalidCountryIdException;

}
