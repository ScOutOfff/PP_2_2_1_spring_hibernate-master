package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Queue;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Transactional
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void deleteAllUsers() {
      List<User> userList = listUsers();
      for (User user: userList) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   @Transactional
   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("FROM User WHERE car_series=:series")
              .setParameter("series", car.getSeries());
//      TypedQuery<Car> query = sessionFactory.getCurrentSession()
//              .createQuery("FROM Car WHERE model=:model AND series=:series")
//              .setParameter("model",car.getModel())
//              .setParameter("series", car.getSeries());
//      List<Car> carList = query.getResultList();
//      if (!carList.isEmpty()) {
//         Car findCar = carList.get(0);
//         List<User> userList = listUsers();
//         User user = userList.stream()
//                 .filter(x -> x.getCar().equals(findCar))
//                 .findFirst()
//                 .orElse(null);
//         return user;
//      }
      return query.getSingleResult();
   }


}
