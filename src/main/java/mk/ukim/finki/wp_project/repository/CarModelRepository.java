package mk.ukim.finki.wp_project.repository;

import mk.ukim.finki.wp_project.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository  extends JpaRepository<CarModel, Long> {
}
