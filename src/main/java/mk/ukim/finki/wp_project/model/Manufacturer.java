package mk.ukim.finki.wp_project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Country country;
    @OneToMany
    private List<CarModel> carModels;

    public Manufacturer() {
    }

    public Manufacturer(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
