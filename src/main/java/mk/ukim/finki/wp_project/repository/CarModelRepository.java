package mk.ukim.finki.wp_project.repository;

import mk.ukim.finki.wp_project.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepository  extends JpaRepository<CarModel, Long> {
}
