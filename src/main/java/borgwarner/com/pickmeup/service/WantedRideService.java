package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.WantedRide;

import java.util.List;

public interface WantedRideService {

    List<WantedRide> listOfWantedRide();

    WantedRide findWantedRideByID(int theID);

    List<WantedRide> findWantedRidesOfSpecificUser(int theID);
}
