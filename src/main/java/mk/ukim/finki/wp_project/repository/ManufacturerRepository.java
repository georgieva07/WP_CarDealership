package mk.ukim.finki.wp_project.repository;

import mk.ukim.finki.wp_project.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository  extends JpaRepository<Manufacturer, Long> {
}
