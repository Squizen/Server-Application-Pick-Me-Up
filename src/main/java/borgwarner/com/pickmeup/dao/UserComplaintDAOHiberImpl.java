package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.entity.UserComplaint;
import borgwarner.com.pickmeup.support.UserComplaintSupport;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Time;
import java.util.List;

@Repository
public class UserComplaintDAOHiberImpl implements UserComplaintDAO {

    private EntityManager entityManager;

    @Autowired
    public UserComplaintDAOHiberImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<UserComplaint> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<UserComplaint> listOfUserComplaints = session.createQuery("from UserComplaint", UserComplaint.class).getResultList();
        return listOfUserComplaints;
    }

    @Override
    public UserComplaint findUserComplaintById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        UserComplaint userComplaint;
        try{
            userComplaint = session.get(UserComplaint.class, theID);
            return userComplaint;
        }catch(NoResultException nre){
            throw new RuntimeException("Result not found");
        }
    }

    @Override
    public UserComplaint addUserComplaint(UserComplaintSupport userComplaintSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user;
        try{
            user = session.get(User.class, userComplaintSupport.getId_user());
            UserComplaint userComplaint = new UserComplaint();
            userComplaint.setId_complaint(0);
            userComplaint.setId_user(user);
            userComplaint.setComplaint(userComplaintSupport.getComplaint());
            userComplaint.setTime_of_addition(userComplaintSupport.getTime_of_addition());
            userComplaint.setDate_of_addition(userComplaintSupport.getDate_of_addition());
            session.saveOrUpdate(userComplaint);
            return userComplaint;
        }catch(NoResultException nre){
            throw new RuntimeException("User not found");
        }
    }
}
