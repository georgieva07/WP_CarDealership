package mk.ukim.finki.wp_project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CarInStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Salon salon;

    private Integer quantity;

    public CarInStock() {
    }

    public CarInStock(Car car, Salon salon, Integer quantity) {
        this.car = car;
        this.salon = salon;
        this.quantity = quantity;
    }
}
