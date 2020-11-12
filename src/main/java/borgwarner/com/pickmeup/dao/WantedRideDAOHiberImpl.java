package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.WantedRide;
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
}
