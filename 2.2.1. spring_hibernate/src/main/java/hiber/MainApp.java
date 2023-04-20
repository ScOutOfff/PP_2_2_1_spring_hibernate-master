package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        carService.add(new Car(41, "Audi"));
        carService.add(new Car(22, "BMW"));
        carService.add(new Car(33, "MERS"));
        carService.add(new Car(443, "Toyota"));

        List<Car> cars = carService.listCars();

        userService.add(new User("John", "Favreau", "user1@mail.ru", cars.get(0)));
        userService.add(new User("Jack", "Favreau", "user1@mail.ru", cars.get(1)));
        userService.add(new User("Igor", "Favreau", "user1@mail.ru", cars.get(2)));
        userService.add(new User("Vasa", "Favreau", "user1@mail.ru", cars.get(3)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        for (Car car : cars) {
            System.out.println(car.toString());
        }

        System.out.println(userService.getUserByCar(cars.get(0)) + "car = \n" + cars.get(0));
        System.out.println(userService.getUserByCar(cars.get(2)) + "car = \n" + cars.get(1));
        System.out.println(userService.getUserByCar(cars.get(1)) + "car = \n" + cars.get(2));
        System.out.println(userService.getUserByCar(cars.get(3)) + "car = \n" + cars.get(3));

        context.close();
    }
}
