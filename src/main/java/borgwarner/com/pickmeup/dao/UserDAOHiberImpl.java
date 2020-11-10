package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.support.UserCreation;
import borgwarner.com.pickmeup.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOHiberImpl implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAOHiberImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    // Request number 1
    @Override
    public User findUserById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, theID);
        return user;
    }
    // Request number 2
    @Override
    public List<User> findAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> listOfAllUsers = session.createQuery("FROM User", User.class).getResultList();
        return listOfAllUsers;
    }
    // Request number 3
    @Override
    public List<User> findAllActiveUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> listOfActiveUsers = session.createQuery("FROM User user WHERE user.is_active = true").getResultList();
        return listOfActiveUsers;
    }
    // Request number 4
    @Override
    public List<User> findAllInactiveUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> listOfInactiveUsers = session.createQuery("FROM User user WHERE user.is_active = false").getResultList();
        return listOfInactiveUsers;
    }
    // Request number 5
    @Override
    public User addUserWithActivationCodeSerialNumber(UserCreation userCreation) {
        User user = new User();
        return user;
    }
}
