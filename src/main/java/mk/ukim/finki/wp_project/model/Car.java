package mk.ukim.finki.wp_project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int doors;
    private String engine;
    private String body;
    private String turbo;
    private String color;
    private Double price;
    private String image;
    private String name;

    @ManyToOne
    private CarModel model;

    public Car() {
    }

    public Car(String name, CarModel model, String body, String engine, String turbo, int doors, String color, Double price, String image) {
        this.model = model;
        this.body = body;
        this.engine = engine;
        this.turbo = turbo;
        this.doors = doors;
        this.color = color;
        this.price = price;
        this.image = image;
        this.name = name;
    }
}
