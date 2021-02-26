package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCarModelIdException;
import mk.ukim.finki.wp_project.repository.CarModelRepository;
import mk.ukim.finki.wp_project.service.CarModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
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
    public CarModel create(String name, Manufacturer manufacturer) {
        CarModel carModel = new CarModel(name, manufacturer);
        return this.carModelRepository.save(carModel);
    }
}
