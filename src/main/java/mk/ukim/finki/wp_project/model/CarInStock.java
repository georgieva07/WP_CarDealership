package mk.ukim.finki.wp_project.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class CarInStock {
    @Id
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Salon salon;

    private Integer quantity;
}
