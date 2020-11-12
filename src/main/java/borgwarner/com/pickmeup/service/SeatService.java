package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.Seat;
import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.SeatSupport;

import java.util.List;

public interface SeatService {

    List<Seat> getListOfAllSeats();

    Seat findSeatById(int theID);

    List<Seat> findAllSeatsOfSpecificOfferedRide(int theID);

    Response addNewSeat(SeatSupport seatSupport);

    Response deleteSeatByID(int theID);


}
