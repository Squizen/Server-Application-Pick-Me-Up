package borgwarner.com.pickmeup.dao;

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

    @Override
    public List<User> findAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> listOfAllUsers = session.createQuery("from User", User.class).getResultList();
        return listOfAllUsers;
    }
}
