package mk.ukim.finki.wp_project.repository;

import mk.ukim.finki.wp_project.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository  extends JpaRepository<Country, Long> {
}
