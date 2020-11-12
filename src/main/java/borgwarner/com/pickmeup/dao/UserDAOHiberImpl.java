package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.UserSupport;
import borgwarner.com.pickmeup.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAOHiberImpl implements UserDAO {

    private EntityManager entityManager;
    private ActivationCodeDAO activationCodeDAO;

    @Autowired
    public UserDAOHiberImpl(EntityManager entityManager, ActivationCodeDAO activationCodeDAO) {
        this.entityManager = entityManager;
        this.activationCodeDAO = activationCodeDAO;
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
    public Response addUserWithActivationCodeSerialNumber(UserSupport userSupport) {
        ActivationCode activationCode = activationCodeDAO.findActivationCodeBySerialNumber(userSupport.getSerialNumber());
        Response response = new Response();
        if (activationCode == null) {
            response.setSuccesful(false);
            response.setMsg("ActivationCode does not exist in database");
            return response;
//            throw new RuntimeException("ActivationCode does not exist in database // ");
        }
        if (activationCode.getUser() != null) {
            response.setSuccesful(false);
            response.setMsg("ActivationCode is already taken ! ");
            return response;
//            throw new RuntimeException("ActivationCode is already taken ! // ");
        }
        User user = new User();
        user.setId_user(0);
        user.setActivationCode(activationCode);
        user.setUser_name(userSupport.getUser_name());
        user.setUser_surname(userSupport.getUser_surname());
        user.setUser_email(userSupport.getUser_email());
        user.setUser_password(userSupport.getUser_password());
        user.setUser_phone_number(userSupport.getUser_phone_number());
        user.setUser_car(userSupport.getUser_car());
        user.setUser_description(userSupport.getUser_description());
        user.setIs_active(userSupport.isIs_active());

        Session session = entityManager.unwrap(Session.class);
        session.save(user);
        response.setSuccesful(true);
        response.setMsg("User successfuly added to database");
        return response;
    }

    // Request number 6
    @Override
    public Response deleteUserById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        User user;
        try {
            user = session.get(User.class, theID);
        } catch (NoResultException ex) {
            response.setMsg("User with ID " + theID + " not found");
            response.setSuccesful(false);
            return response;
        }
        if(user != null){
            Query query = session.createQuery("DELETE FROM User user WHERE user.id_user = " + theID);
            query.executeUpdate();
//            session.remove(user);
            response.setSuccesful(true);
            response.setMsg("User with ID " + theID + " successfully deleted");
        } else {
            response.setMsg("User with ID " + theID + " not found");
            response.setSuccesful(false);
        }
        return response;
    }
    // Request number 7
    @Override
    public Response updateUser(UserSupport userSupport) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        User user;
        try{
             user = session.get(User.class, userSupport.getId_user());
        }catch (NoResultException ex){
            response.setMsg("User with ID " + userSupport.getId_user() + " not found");
            response.setSuccesful(false);
            return response;
        }
        if(user != null){
            user.setId_user(userSupport.getId_user());
            user.setUser_name(userSupport.getUser_name());
            user.setUser_surname(userSupport.getUser_surname());
            user.setUser_email(userSupport.getUser_email());
            user.setUser_password(userSupport.getUser_password());
            user.setUser_phone_number(userSupport.getUser_phone_number());
            user.setUser_car(userSupport.getUser_car());
            user.setUser_description(userSupport.getUser_description());
            session.saveOrUpdate(user);
            response.setMsg("User with ID " + userSupport.getId_user() + " has been successfully updated ");
            response.setSuccesful(true);
            return response;
        } else {
            response.setMsg("User with ID " + userSupport.getId_user() + " not found");
            response.setSuccesful(false);
            return response;
        }
    }

    @Override
    public User loginToApplication(String email, String password) {
        Session session = entityManager.unwrap(Session.class);
        User user = null;
        try{
            user = session.createQuery("FROM User user WHERE user.user_email = " +"\'"+email+"\'" + "AND user.user_password = " +"\'"+password+"\'", User.class).getSingleResult();
        }catch(NoResultException nre){
            nre.printStackTrace();
        }
        if(user != null){
            return user;
        } else {
            throw new RuntimeException("Email or password is wrong");
        }
    }
}
