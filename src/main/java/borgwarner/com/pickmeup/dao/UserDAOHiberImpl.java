package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.UserStatistics;
import borgwarner.com.pickmeup.support.UserSupport;
import borgwarner.com.pickmeup.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOHiberImpl implements UserDAO {

    private EntityManager entityManager;
    private ActivationCodeDAO activationCodeDAO;
    private WantedRideDAO wantedRideDAO;

    @Autowired
    public UserDAOHiberImpl(EntityManager entityManager, ActivationCodeDAO activationCodeDAO, WantedRideDAO wantedRideDAO) {
        this.entityManager = entityManager;
        this.activationCodeDAO = activationCodeDAO;
        this.wantedRideDAO = wantedRideDAO;
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
    public User addUserWithActivationCodeSerialNumber(UserSupport userSupport) {
        ActivationCode activationCode = activationCodeDAO.findActivationCodeBySerialNumber(userSupport.getSerialNumber());
        Response response = new Response();
        if (activationCode == null) {
            throw new RuntimeException("ActivationCode does not exist in database // ");
        }
        if (activationCode.getUser() != null) {
            throw new RuntimeException("ActivationCode is already taken ! // ");
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
        return user;
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
            user.getActivationCode().setUser(null);
            user.setActivationCode(null);
            session.remove(user);
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

    @Override
    public Response checkIfEmailIsAvailable(String email) {
        Session session = entityManager.unwrap(Session.class);
        User user = null;
        try{
           user = session.createQuery("FROM User user WHERE user.user_email = " + "\'" + email + "\'", User.class).getSingleResult();
        }catch(NoResultException nre){
            return new Response(true, "Email is free to use");
        }
        if(user == null){
            return new Response(true, "Email is free to use");
        } else {
            return new Response(false, "Email already taken");
        }
    }

    @Override
    public UserStatistics requestUserStatistics(int userID) {
        UserStatistics userStatistics = new UserStatistics();
        Session session = entityManager.unwrap(Session.class);

        List<OfferedRide> listOfAllOfferedRides = session.createQuery("from OfferedRide", OfferedRide.class).getResultList();
        List<OfferedRide> listOfOfferedRideBySpecificUser = new ArrayList<>();
        for (OfferedRide off:
                listOfAllOfferedRides) {
            if(off.getUser().getId_user() == userID){
                listOfOfferedRideBySpecificUser.add(off);
            }
        }
        Date date = new java.sql.Date(System.currentTimeMillis());
        Date date2 = Date.valueOf(date.toString());
        Time time = new Time(System.currentTimeMillis());
        Time time2 = Time.valueOf(time.toString());
        int numberOfOfferedRidesWithPassengers = 0;
        int numberOfPassengers = 0;
        for (OfferedRide off: listOfOfferedRideBySpecificUser) {
            if(off.getListOfSeats() != null){
                if(!off.getListOfSeats().isEmpty()){
                    numberOfPassengers += off.getListOfSeats().size();
                    int resultOfComparision = off.getDate_of_ride().compareTo(date2);
                    if(resultOfComparision == -1){
                        numberOfOfferedRidesWithPassengers++;
                    } else if (resultOfComparision == 0){
                        if(off.getTime_of_ride().compareTo(time2) == -1){
                            numberOfOfferedRidesWithPassengers++;
                        }
                    }
                }
            }
        }
        userStatistics.setOfferedRides(numberOfOfferedRidesWithPassengers);
        userStatistics.setPassengersTransported(numberOfPassengers);
        List<WantedRide> listOfWantedRideBySpecificUser = wantedRideDAO.findWantedRidesOfSpecificUser(userID);
        int numberOfRidesAsPassenger = 0;
        for (WantedRide wantedRide: listOfWantedRideBySpecificUser) {
            if(wantedRide.getId_user_driver() != null){
                int resultOfComparision = wantedRide.getDate_of_ride().compareTo(date2);
                if(resultOfComparision == -1){
                    numberOfRidesAsPassenger++;
                } else if (resultOfComparision == 0){
                    if(wantedRide.getTime_of_ride().compareTo(time2) == -1){
                        numberOfRidesAsPassenger++;
                    }
                }
            }
        }
        userStatistics.setRidesAsPassenger(numberOfRidesAsPassenger);
        return userStatistics;
    }
}
