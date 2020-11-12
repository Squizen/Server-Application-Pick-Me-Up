package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.WantedRideSupport;

import java.util.List;

public interface WantedRideService {

    List<WantedRide> listOfWantedRide();

    WantedRide findWantedRideByID(int theID);

    List<WantedRide> findWantedRidesOfSpecificUser(int theID);

    Response addNewWantedRide(WantedRideSupport wantedRideSupport);

    Response updateWantedRide(WantedRideSupport wantedRideSupport);
}
