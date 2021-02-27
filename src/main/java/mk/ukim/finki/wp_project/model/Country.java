package mk.ukim.finki.wp_project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany
    private List<Manufacturer> manufacturers;
    @OneToMany
    private List<Salon> salons;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }
}
