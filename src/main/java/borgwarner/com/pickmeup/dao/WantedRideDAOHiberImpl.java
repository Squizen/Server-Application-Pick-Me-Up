package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.WantedRideSupport;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WantedRideDAOHiberImpl implements WantedRideDAO {

    private EntityManager entityManager;

    @Autowired
    public WantedRideDAOHiberImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<WantedRide> findAllWantedRides() {
        Session session = entityManager.unwrap(Session.class);
        List<WantedRide> listOfWantedRides = session.createQuery("FROM WantedRide", WantedRide.class).getResultList();
        return listOfWantedRides;
    }

    @Override
    public WantedRide findWantedRideByID(int theID) {
        Session session = entityManager.unwrap(Session.class);
        WantedRide wantedRide = null;
        try{
            wantedRide = session.get(WantedRide.class, theID);
        }catch(NoResultException nre){
            nre.printStackTrace();
        }
        if(wantedRide != null){
            return wantedRide;
        } else {
            throw new RuntimeException("Wanted Ride with ID = " + theID + " not found");
        }
    }

    @Override
    public List<WantedRide> findWantedRidesOfSpecificUser(int theID) {
        List<WantedRide> listOfAllWantedRides = findAllWantedRides();
        List<WantedRide> listOfWantedRidesOfSpecificUser = new ArrayList<>();
        for (WantedRide wantedRide : listOfAllWantedRides){
            if(wantedRide.getUser().getId_user() == theID){
                listOfWantedRidesOfSpecificUser.add(wantedRide);
            }
        }
        return listOfWantedRidesOfSpecificUser;
    }

    @Override
    public Response addNewWantedRide(WantedRideSupport wantedRideSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, wantedRideSupport.getId_user());
        if(user != null){
            WantedRide wantedRide = new WantedRide();
            wantedRide.setId_wanted_ride(0);
            wantedRide.setDate_of_ride(wantedRideSupport.getDate_of_ride());
            wantedRide.setTime_of_ride(wantedRideSupport.getTime_of_ride());
            wantedRide.setRide_category(wantedRideSupport.getRide_category());
            wantedRide.setFrom_where(wantedRideSupport.getFrom_where());
            wantedRide.setTo_where(wantedRideSupport.getTo_where());
            wantedRide.setUser_comment(wantedRideSupport.getUser_comment());
            wantedRide.setUser(user);
            user.addToListOfWantedRides(wantedRide);
            session.saveOrUpdate(user);
            session.saveOrUpdate(wantedRide);
            return new Response(true, "WantedRide has been successfully created");
        } else {
            return new Response(false, "User with ID " + wantedRideSupport.getId_user() + " does not exist in database");
        }
    }

    @Override
    public Response updateWantedRide(WantedRideSupport wantedRideSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, wantedRideSupport.getId_user());
        if(user != null){
            WantedRide wantedRide = new WantedRide();
            wantedRide.setId_wanted_ride(wantedRideSupport.getId_wanted_ride());
            wantedRide.setDate_of_ride(wantedRideSupport.getDate_of_ride());
            wantedRide.setTime_of_ride(wantedRideSupport.getTime_of_ride());
            wantedRide.setRide_category(wantedRideSupport.getRide_category());
            wantedRide.setFrom_where(wantedRideSupport.getFrom_where());
            wantedRide.setTo_where(wantedRideSupport.getTo_where());
            wantedRide.setUser_comment(wantedRideSupport.getUser_comment());
            wantedRide.setUser(user);
            user.addToListOfWantedRides(wantedRide);
            session.saveOrUpdate(user);
            session.saveOrUpdate(wantedRide);
            return new Response(true, "WantedRide has been successfully updated");
        } else {
            return new Response(false, "User with ID " + wantedRideSupport.getId_user() + " does not exist in database");
        }
    }
}
