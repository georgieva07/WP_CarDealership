package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.User;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;

import java.util.List;

public interface SalonService {
    Salon findById(Long id) throws InvalidSalonIdException;

    List<Salon> listAll();

    Salon create(String address, String city, Long countryId, User user) throws InvalidCountryIdException;

    Salon update(Long id, String address, String city, Long countryId, User user) throws InvalidCountryIdException, InvalidSalonIdException;

    Salon delete(Long id) throws InvalidSalonIdException;
}
