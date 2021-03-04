package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.CarModel;
import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp_project.repository.ManufacturerRepository;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final CountryService countryService;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, CountryService countryService) {
        this.manufacturerRepository = manufacturerRepository;
        this.countryService = countryService;
    }

    @Override
    public Manufacturer findById(Long id) throws InvalidManufacturerIdException {
        return this.manufacturerRepository.findById(id).orElseThrow(InvalidManufacturerIdException::new);
    }

    @Override
    public List<Manufacturer> listAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer create(String name, Long countryId, String image) throws InvalidCountryIdException {
        Country country = this.countryService.findById(countryId);
        Manufacturer manufacturer = new Manufacturer(name, country, image);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Long id, String name, Long countryId, String image) throws InvalidCountryIdException, InvalidManufacturerIdException {
        Manufacturer manufacturer = this.findById(id);
        Country country = this.countryService.findById(countryId);
        manufacturer.setName(name);
        manufacturer.setCountry(country);
        manufacturer.setImage(image);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer delete(Long id) throws InvalidManufacturerIdException {
        Manufacturer manufacturer = this.findById(id);
        this.manufacturerRepository.delete(manufacturer);
        return manufacturer;
    }

    @Override
    public CarModel removeCarModelFromManufacturer(Long manufacturerId, CarModel carModel) throws InvalidManufacturerIdException {
        Manufacturer manufacturer = this.findById(manufacturerId);
        manufacturer.getCarModels().remove(carModel);
        return carModel;
    }

    @Override
    public CarModel addCarModelToManufacturer(Long manufacturerId, CarModel carModel) throws InvalidManufacturerIdException {
        Manufacturer manufacturer = this.findById(manufacturerId);
        manufacturer.getCarModels().add(carModel);
        return carModel;
    }
}
