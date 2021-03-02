package mk.ukim.finki.wp_project.repository;

import mk.ukim.finki.wp_project.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository  extends JpaRepository<Salon, Long> {
}
