package mk.ukim.finki.wp_project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Manufacturer manufacturer;
    @OneToMany
    private List<Car> cars;

    public CarModel() {
    }

    public CarModel(String name, Manufacturer manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }
}