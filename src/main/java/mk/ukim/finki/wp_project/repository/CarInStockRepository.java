package mk.ukim.finki.wp_project.repository;

import mk.ukim.finki.wp_project.model.CarInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInStockRepository extends JpaRepository<CarInStock, Long> {
}
