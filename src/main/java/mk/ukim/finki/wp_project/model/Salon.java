package mk.ukim.finki.wp_project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    @ManyToOne
    private Country country;

    public Salon() {
    }

    public Salon(String address, Country country) {
        this.address = address;
        this.country = country;
    }
}