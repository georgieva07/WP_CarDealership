package mk.ukim.finki.wp_project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private String city;
    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "salon")
    private List<CarInStock> carsInStock;

    @OneToOne
    private User manager;

    public Salon() {
    }

    public Salon(String address, String city, Country country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }
}
