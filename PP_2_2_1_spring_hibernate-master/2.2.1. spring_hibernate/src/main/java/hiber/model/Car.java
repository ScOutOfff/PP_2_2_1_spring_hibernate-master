package hiber.model;

import javax.persistence.*;
import java.io.Serializable;

/**Columns - series and model*/

@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    @Id
    private int series;

    private String model;

    public Car() {

    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }
    public Car(User user, int series, String model) {
        this.user = user;
        this.series = series;
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "model - " + model +
                "\nseries - " + series +
                '\n';
    }
}
