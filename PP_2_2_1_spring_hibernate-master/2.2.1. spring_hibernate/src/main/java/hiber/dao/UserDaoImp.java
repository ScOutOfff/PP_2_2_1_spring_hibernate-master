package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

   @Transactional
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
              .createQuery("FROM User user LEFT OUTER JOIN FETCH user.car car WHERE car.series=:series AND car.model=:model")
              .setParameter("series", car.getSeries())
              .setParameter("model", car.getModel());
      return query.getSingleResult();
   }


}
