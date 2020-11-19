package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.support.OfferedRideSupport;
import borgwarner.com.pickmeup.support.Response;

import java.util.List;

public interface OfferedRideService {

    List<OfferedRide> listOfAllOfferedRides();

    OfferedRide findOfferedRideById(int theID);

    List<OfferedRide> listOfRidesToCompany(String from_where, String startingDay, String startingMoment);

    List<OfferedRide> listOfRidesFromCompany(String to_where, String startingDay, String startingMoment);

    OfferedRide addNewOfferedRide(OfferedRideSupport offeredRideSupport);

    Response updateOfferedRide(OfferedRideSupport offeredRideSupport);

    Response deleteOfferedRideById(int theID);

    List<OfferedRide> getListOfOfferedRidesOfSpecificUser(int theID);
}
