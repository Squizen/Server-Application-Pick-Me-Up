package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.support.OfferedRideSupport;
import borgwarner.com.pickmeup.support.Response;

import java.util.List;

public interface OfferedRideDAO {

    List<OfferedRide> findAllOfferedRides();

    OfferedRide findOfferedRideById(int theID);

    List<OfferedRide> listOfRidesToCompany(String from_where, String startingDay, String startingMoment);

    List<OfferedRide> listOfRidesFromCompany(String to_where, String startingDay, String startingMoment);

    OfferedRide addNewOfferedRide(OfferedRideSupport offeredRideSupport);

    Response updateOfferedRide(OfferedRideSupport offeredRideSupport);

    Response deleteOfferedRideById(int theID);

    List<OfferedRide> getListOfOfferedRidesOfSpecificUser(int userID);

    List<OfferedRide> getListOfOfferedRidesOfSpecificUserBeforeCurrentMoment(int userID);


}
