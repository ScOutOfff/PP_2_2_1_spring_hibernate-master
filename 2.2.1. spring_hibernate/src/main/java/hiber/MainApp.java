package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.deleteAllUsers();

      User user1 = new User("John", "Favro", "user1@mail.ru");
      User user2 = new User("Jeck", "Favro", "user2@mail.ru");
      User user3 = new User("Igor", "Favro", "user3@mail.ru");
      User user4 = new User("Vasya", "Favro", "user4@mail.ru");

//      Car car1 = new Car(user1, 1, "Audi");
//      Car car2 = new Car(user2, 2, "BMW");
//      Car car3 = new Car(user3, 3, "MERS");
//      Car car4 = new Car(user4, 4, "Toyota");

      user1.setCar(new Car(user1, 1, "Audi"));
      user2.setCar(new Car(user2, 2, "BMW"));
      user3.setCar(new Car(user3, 3, "MERS"));
      user4.setCar(new Car(user4, 4, "Toyota"));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
      }

      List<Car> cars = userService.listCars();
      for (Car car: cars) {
         System.out.println(car.toString());
      }
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      System.out.println(userService.getUserByCar(cars.get(0)));



      context.close();
   }
}
