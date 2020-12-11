package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.WantedRideSupport;
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
public class WantedRideDAOHiberImpl implements WantedRideDAO {

    private EntityManager entityManager;

    @Autowired
    public WantedRideDAOHiberImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    private boolean isRideAfterTimeParameter(WantedRide wantedRide, String timeParam) {
        Time time = Time.valueOf(timeParam);
        if (wantedRide.getTime_of_ride().compareTo(time) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private int isRideTodayOrAfterDateParameter(WantedRide wantedRide, String startingMoment) {
        Date date = Date.valueOf(startingMoment);
        if (wantedRide.getDate_of_ride().compareTo(date) > 0) {
            return 1;
        } else if (wantedRide.getDate_of_ride().compareTo(date) == 0) {
            return 0;
        } else {
            return -1;
        }
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
    public WantedRide addNewWantedRide(WantedRideSupport wantedRideSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, wantedRideSupport.getId_user());
        if(user != null){
            WantedRide wantedRide = new WantedRide();
            wantedRide.setId_wanted_ride(0);
            wantedRide.setUser_phone_number(wantedRideSupport.getUser_phone_number());
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
            return wantedRide;
        } else {
            throw new RuntimeException("User with ID " + wantedRideSupport.getId_user() + " does not exist in database");
        }
    }

    @Override
    public Response updateWantedRide(WantedRideSupport wantedRideSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, wantedRideSupport.getId_user());
        User driver = session.get(User.class, wantedRideSupport.getId_user_driver());
        if(user != null){
            WantedRide wantedRide = new WantedRide();
            wantedRide.setId_wanted_ride(wantedRideSupport.getId_wanted_ride());
            wantedRide.setUser_phone_number(wantedRideSupport.getUser_phone_number());
            wantedRide.setDate_of_ride(wantedRideSupport.getDate_of_ride());
            wantedRide.setTime_of_ride(wantedRideSupport.getTime_of_ride());
            wantedRide.setRide_category(wantedRideSupport.getRide_category());
            wantedRide.setFrom_where(wantedRideSupport.getFrom_where());
            wantedRide.setTo_where(wantedRideSupport.getTo_where());
            wantedRide.setUser_comment(wantedRideSupport.getUser_comment());

            if(driver != null){
                wantedRide.setId_user_driver(driver);
                session.saveOrUpdate(driver);
            }

            user.addToListOfWantedRides(wantedRide);
            wantedRide.setUser(user);

            session.saveOrUpdate(user);
            session.saveOrUpdate(wantedRide);

            return new Response(true, "WantedRide has been successfully updated");
        } else {
            return new Response(false, "User with ID " + wantedRideSupport.getId_user() + " does not exist in database");
        }
    }

    @Override
    public Response deleteWantedRideById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        WantedRide wantedRide;
        Response response = new Response();
        try{
            wantedRide = session.get(WantedRide.class, theID);
        }catch(NoResultException nre){
            nre.printStackTrace();
            response.setSuccesful(false);
            response.setMsg("WantedRide with ID = " + theID + " does not exists in database");
            return response;
        }
        if(wantedRide != null){
            session.remove(wantedRide);
            response.setSuccesful(true);
            response.setMsg("WantedRide has been successfully removed from database");
        } else {
            response.setSuccesful(false);
            response.setMsg("WantedRide with ID = " + theID + " does not exists in database");
        }
        return response;
    }

    @Override
    public List<WantedRide> findWantedRidesToCompany(String from_where, String startingDay, String startingMoment) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "";
        if (from_where.isEmpty()) {
            hql = "FROM WantedRide";
        } else {
            hql = "FROM WantedRide wr WHERE wr.from_where = " + "\'" + from_where + "\'";
        }
        List<WantedRide> listOfWantedRides = session.createQuery(hql, WantedRide.class).getResultList();
        List<WantedRide> listOfWantedRidesAfterDateTimeParam = new ArrayList<>();
        for (WantedRide wantedRide : listOfWantedRides) {
            int resultOfDateComparision = isRideTodayOrAfterDateParameter(wantedRide, startingDay);
            if (resultOfDateComparision == 1 && wantedRide.getRide_category() == 0) {
                listOfWantedRidesAfterDateTimeParam.add(wantedRide);
            } else if (resultOfDateComparision == 0) {
                if (isRideAfterTimeParameter(wantedRide, startingMoment) && wantedRide.getRide_category() == 0) {
                    listOfWantedRidesAfterDateTimeParam.add(wantedRide);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return listOfWantedRidesAfterDateTimeParam;
    }

    @Override
    public List<WantedRide> findWantedRidesFromCompany(String to_where, String startingDay, String startingMoment) {
        Session session = entityManager.unwrap(Session.class);
        String hql = "";
        if (to_where.isEmpty()) {
            hql = "FROM WantedRide";
        } else {
            hql = "FROM WantedRide wr WHERE wr.to_where = " + "\'" + to_where + "\'";
        }
        List<WantedRide> listOfWantedRides = session.createQuery(hql, WantedRide.class).getResultList();
        List<WantedRide> listOfWantedRidesAfterDateTimeParam = new ArrayList<>();
        for (WantedRide wantedRide : listOfWantedRides) {
            int resultOfDateComparision = isRideTodayOrAfterDateParameter(wantedRide, startingDay);
            if (resultOfDateComparision == 1 && wantedRide.getRide_category() == 1) {
                listOfWantedRidesAfterDateTimeParam.add(wantedRide);
            } else if (resultOfDateComparision == 0) {
                if (isRideAfterTimeParameter(wantedRide, startingMoment) && wantedRide.getRide_category() == 1) {
                    listOfWantedRidesAfterDateTimeParam.add(wantedRide);
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return listOfWantedRidesAfterDateTimeParam;
    }
}
