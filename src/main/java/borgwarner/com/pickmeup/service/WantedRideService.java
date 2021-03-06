package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.WantedRideSupport;

import java.util.List;

public interface WantedRideService {

    List<WantedRide> listOfWantedRide();

    WantedRide findWantedRideByID(int theID);

    List<WantedRide> findWantedRidesOfSpecificUser(int theID);

    WantedRide addNewWantedRide(WantedRideSupport wantedRideSupport);

    Response updateWantedRide(WantedRideSupport wantedRideSupport);

    Response deleteWantedRideById(int theID);

    List<WantedRide> findWantedRidesToCompany(String from_where, String startingDay, String startingMoment);

    List<WantedRide> findWantedRidesFromCompany(String to_where, String startingDay, String startingMoment);

    Response addDriverToExistingWantedRide(int id_wanted_ride, int id_driver);

    Response deleteDriverOfExistingWantedRide(int id_wanted_ride, int id_driver);
}
