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

    public Salon() {
    }

    public Salon(String address, String city, Country country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }
}
