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

      User john = new User("John", "Favro", "user1@mail.ru");
      User jeck = new User("Jeck", "Favro", "user2@mail.ru");
      User igor = new User("Igor", "Favro", "user3@mail.ru");
      User vasya = new User("Vasya", "Favro", "user4@mail.ru");

      Car audi = new Car(john, 1, "Audi");
      Car bmw = new Car(jeck, 2, "BMW");
      Car mers = new Car(igor, 3, "MERS");
      Car toyota = new Car(vasya, 4, "Toyota");

      john.setCar(audi);
      jeck.setCar(bmw);
      igor.setCar(mers);
      vasya.setCar(toyota);

      userService.add(john);
      userService.add(jeck);
      userService.add(igor);
      userService.add(vasya);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
