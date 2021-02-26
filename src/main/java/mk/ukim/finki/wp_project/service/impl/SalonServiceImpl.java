package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.Salon;
import mk.ukim.finki.wp_project.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.wp_project.model.exceptions.InvalidSalonIdException;
import mk.ukim.finki.wp_project.repository.CountryRepository;
import mk.ukim.finki.wp_project.repository.SalonRepository;
import mk.ukim.finki.wp_project.service.CountryService;
import mk.ukim.finki.wp_project.service.SalonService;

import java.util.List;

public class SalonServiceImpl implements SalonService {
    private final SalonRepository salonRepository;
    private final CountryService countryService;

    public SalonServiceImpl(SalonRepository salonRepository, CountryService countryService) {
        this.salonRepository = salonRepository;
        this.countryService = countryService;
    }

    @Override
    public Salon findById(Long id) throws InvalidSalonIdException {
        return this.salonRepository.findById(id).orElseThrow(InvalidSalonIdException::new);
    }

    @Override
    public List<Salon> listAll() {
        return this.salonRepository.findAll();
    }

    @Override
    public Salon create(String address, Long countryId) throws InvalidCountryIdException {
        Country country = this.countryService.findById(countryId);
        Salon salon = new Salon(address, country);
        return this.salonRepository.save(salon);
    }
}
