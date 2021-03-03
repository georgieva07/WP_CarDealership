package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.Car;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;

import java.util.List;

public interface CarService {
    Car findById(Long id) throws InvalidCarIdException;

    List<Car> listAll();

    Car create(Long modelId, String body, String engine, String turbo, int doors, String color, Double price, String image) throws InvalidCarModelIdException;

    Car update(Long id, Long carModelId, String body, String engine, String turbo, Integer doors, String color, Double price, String image) throws InvalidCarModelIdException, InvalidCarIdException;

    Car delete(Long id) throws InvalidCarIdException;
}
