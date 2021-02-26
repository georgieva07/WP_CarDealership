package mk.ukim.finki.wp_project.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int doors;
    private String engine;
    private String body;
    private String turbo;
    private String color;
    @ManyToOne
    private CarModel model;

    public Car() {
    }

    public Car(int doors, String engine, String body, String turbo, String color, CarModel model) {
        this.doors = doors;
        this.engine = engine;
        this.body = body;
        this.turbo = turbo;
        this.color = color;
        this.model = model;
    }
}
