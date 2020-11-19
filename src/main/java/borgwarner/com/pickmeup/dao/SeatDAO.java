package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.Seat;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.SeatSupport;

import java.util.List;

public interface SeatDAO {

    List<Seat> getListOfAllSeats();

    Seat findSeatById(int theID);

    List<Seat> findAllSeatsOfSpecificOfferedRide(int theID);

    Seat addNewSeat(SeatSupport seatSupport);

    Response deleteSeatByID(int theID);
}
