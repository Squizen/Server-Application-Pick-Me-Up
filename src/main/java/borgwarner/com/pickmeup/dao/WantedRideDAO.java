package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.WantedRide;

import java.util.List;

public interface WantedRideDAO {

    List<WantedRide> findAllWantedRides();

    WantedRide findWantedRideByID(int theID);

    List<WantedRide> findWantedRidesOfSpecificUser(int theID);
}
