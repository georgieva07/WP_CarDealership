package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Manufacturer;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp_project.repository.CountryRepository;
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
    public Manufacturer create(String name, Long countryId) throws InvalidCountryIdException {
        Country country = this.countryService.findById(countryId);
        Manufacturer manufacturer = new Manufacturer(name, country);
        return this.manufacturerRepository.save(manufacturer);
    }
}
