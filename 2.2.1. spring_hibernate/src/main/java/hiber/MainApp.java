package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.SocketHandler;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car bmw = new Car("BMW", 5);
      Car mercedes = new Car("Mercedes", 200);
      Car lamborgini = new Car("Lamborgini", 10);
      Car audi = new Car("Audi", 7);

      carService.add(bmw);
      carService.add(mercedes);
      carService.add(lamborgini);
      carService.add(audi);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
       userService.add(new User("User1", "Lastname1", "user@mail.ru", bmw));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", mercedes));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", lamborgini));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", audi));


      System.out.println("------------------------------------------------------");

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println("----------------------------------------------------------");

      User user = userService.getUserByCarModelAndSeries("Audi", 7);
      if(user == null){
         System.out.println("Владелец машины не найден ");
      } else {
         System.out.println();
         System.out.println("Владелец машины: " + user.getCar() + " - " + user.getFirstName() +
                 " " + user.getLastName());
      }


      context.close();
   }
}
