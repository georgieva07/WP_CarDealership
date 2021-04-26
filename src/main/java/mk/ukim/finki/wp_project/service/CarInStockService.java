package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.CarInStock;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarInStockIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;

import java.util.List;

public interface CarInStockService {

    CarInStock findById(Long id) throws InvalidCarInStockIdException;

    List<CarInStock> listAll();

    CarInStock create( Long carId, Long salonId, Integer quantity) throws InvalidCarIdException, InvalidSalonIdException;

    CarInStock update(Long id, Long carId, Long salonId, Integer quantity) throws InvalidSalonIdException, InvalidCarIdException, InvalidCarInStockIdException;

    CarInStock delete(Long id) throws InvalidCarInStockIdException;
}
