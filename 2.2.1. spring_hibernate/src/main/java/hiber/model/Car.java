package hiber.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    @OneToOne
    @JoinColumn()
    private User user;

    @Id
    @JoinColumn(name = "series", referencedColumnName = "id")
    private int series;
    private String model;

    public Car() {

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
        return "Car{" +
                "user=" + user.getFirstName() +
                ", series=" + series +
                ", model='" + model + '\'' +
                '}';
    }
}
