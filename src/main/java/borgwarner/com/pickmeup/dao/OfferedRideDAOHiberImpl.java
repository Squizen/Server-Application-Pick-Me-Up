package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.support.OfferedRideSupport;
import borgwarner.com.pickmeup.support.Response;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferedRideDAOHiberImpl implements OfferedRideDAO {

    private EntityManager entityManager;
    private UserDAO userDAO;

    @Autowired
    public OfferedRideDAOHiberImpl(EntityManager entityManager, UserDAO userDAO) {
        this.entityManager = entityManager;
        this.userDAO = userDAO;
    }

    private boolean isRideAfterTimeParameter(OfferedRide offeredRide, String timeParam) {
        Time time = Time.valueOf(timeParam);
        if (offeredRide.getTime_of_ride().compareTo(time) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private int isRideTodayOrAfterDateParameter(OfferedRide offeredRide, String startingMoment) {
        Date date = Date.valueOf(startingMoment);
        if (offeredRide.getDate_of_ride().compareTo(date) > 0) {
            return 1;
        } else if (offeredRide.getDate_of_ride().compareTo(date) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public List<OfferedRide> findAllOfferedRides() {
        Session session = entityManager.unwrap(Session.class);
        List<OfferedRide> listOfAllOfferedRides = session.createQuery("FROM OfferedRide", OfferedRide.class).getResultList();
        return listOfAllOfferedRides;
    }

    @Override
    public OfferedRide findOfferedRideById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        OfferedRide offeredRide = session.get(OfferedRide.class, theID);
        return offeredRide;
    }

    @Override
    public List<OfferedRide> listOfRidesToCompany(String from_where, String startingDay, String startingMoment) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "";
        if (from_where.isEmpty()) {
            hql = "FROM OfferedRide";
        } else {
            hql = "FROM OfferedRide off WHERE off.from_where = " + "\'" + from_where + "\'";
        }
        List<OfferedRide> listOfOfferedRides = session.createQuery(hql, OfferedRide.class).getResultList();
        List<OfferedRide> listOfOfferedRidesAfterDateTimeParam = new ArrayList<>();
        for (OfferedRide offeredRide : listOfOfferedRides) {
            int resultOfDateComparision = isRideTodayOrAfterDateParameter(offeredRide, startingDay);
            if (resultOfDateComparision == 1) {
                listOfOfferedRidesAfterDateTimeParam.add(offeredRide);
            } else if (resultOfDateComparision == 0) {
                if (isRideAfterTimeParameter(offeredRide, startingMoment)) {
                    listOfOfferedRidesAfterDateTimeParam.add(offeredRide);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return listOfOfferedRidesAfterDateTimeParam;
    }

    @Override
    public List<OfferedRide> listOfRidesFromCompany(String to_where, String startingDay, String startingMoment) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "";
        if (to_where.isEmpty()) {
            hql = "FROM OfferedRide";
        } else {
            hql = "FROM OfferedRide off WHERE off.from_where = " + "\'" + to_where + "\'";
        }
        List<OfferedRide> listOfOfferedRides = session.createQuery(hql, OfferedRide.class).getResultList();
        List<OfferedRide> listOfOfferedRidesAfterDateTimeParam = new ArrayList<>();
        for (OfferedRide offeredRide : listOfOfferedRides) {
            int resultOfDateComparision = isRideTodayOrAfterDateParameter(offeredRide, startingDay);
            if (resultOfDateComparision == 1) {
                listOfOfferedRidesAfterDateTimeParam.add(offeredRide);
            } else if (resultOfDateComparision == 0) {
                if (isRideAfterTimeParameter(offeredRide, startingMoment)) {
                    listOfOfferedRidesAfterDateTimeParam.add(offeredRide);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return listOfOfferedRidesAfterDateTimeParam;
    }

    @Override
    public Response addNewOfferedRide(OfferedRideSupport offeredRideSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = userDAO.findUserById(offeredRideSupport.getId_user());
        if (user != null) {
            OfferedRide offeredRide = new OfferedRide();
            offeredRide.setUser(user);
            offeredRide.setDate_of_ride(offeredRideSupport.getDate_of_ride());
            offeredRide.setTime_of_ride(offeredRideSupport.getTime_of_ride());
            offeredRide.setNumber_of_free_seats(offeredRideSupport.getNumber_of_free_seats());
            offeredRide.setRide_category(offeredRideSupport.getNumber_of_free_seats());
            offeredRide.setFrom_where(offeredRideSupport.getFrom_where());
            offeredRide.setTo_where(offeredRideSupport.getTo_where());
            offeredRide.setUser_comment(offeredRideSupport.getUser_comment());
            session.saveOrUpdate(offeredRide);
            return new Response(true, "OfferedRide has been successfully added to database // ");
        } else {
            return new Response(false, "No user found // ");
        }
    }

    @Override
    public Response updateOfferedRide(OfferedRideSupport offeredRideSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = userDAO.findUserById(offeredRideSupport.getId_user());
        if (user != null) {
            OfferedRide offeredRide = new OfferedRide();
            offeredRide.setId_offered_ride(offeredRideSupport.getId_offered_ride());
            offeredRide.setUser(user);
            offeredRide.setDate_of_ride(offeredRideSupport.getDate_of_ride());
            offeredRide.setTime_of_ride(offeredRideSupport.getTime_of_ride());
            offeredRide.setNumber_of_free_seats(offeredRideSupport.getNumber_of_free_seats());
            offeredRide.setRide_category(offeredRideSupport.getNumber_of_free_seats());
            offeredRide.setFrom_where(offeredRideSupport.getFrom_where());
            offeredRide.setTo_where(offeredRideSupport.getTo_where());
            offeredRide.setUser_comment(offeredRideSupport.getUser_comment());
            session.saveOrUpdate(offeredRide);
            return new Response(true, "OfferedRide has been successfully updated");
        } else {
            return new Response(false, "No user found");
        }
    }

    @Override
    public Response deleteOfferedRideById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        OfferedRide offeredRide;
        try{
            offeredRide = session.get(OfferedRide.class, theID);
        }catch(NoResultException exc){
            return new Response(false, "Offered Ride with ID = " + theID + " not found");
        }
        if(offeredRide != null){
            session.createQuery("DELETE FROM OfferedRide ofr WHERE ofr.id_offered_ride = " + theID).executeUpdate();
            return new Response(true, "Offered Ride with ID = " + theID + " successfully deleted");
        }
        return new Response(false, "Offered Ride with ID = " + theID + " not found");
    }

    @Override
    public List<OfferedRide> listOfOfferedRidesOfSpecificUser(int userID) {
        return null;
    }
}
