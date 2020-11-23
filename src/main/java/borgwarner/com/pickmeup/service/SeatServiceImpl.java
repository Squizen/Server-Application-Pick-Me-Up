package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.SeatDAO;
import borgwarner.com.pickmeup.entity.Seat;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.SeatSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private SeatDAO seatDAO;

    @Autowired
    public SeatServiceImpl(SeatDAO seatDAO){
        this.seatDAO = seatDAO;
    }

    @Override
    @Transactional
    public List<Seat> getListOfAllSeats() {
        return seatDAO.getListOfAllSeats();
    }

    @Override
    @Transactional
    public Seat findSeatById(int theID) {
        return seatDAO.findSeatById(theID);
    }

    @Override
    @Transactional
    public List<Seat> findAllSeatsOfSpecificOfferedRide(int theID) {
        return seatDAO.findAllSeatsOfSpecificOfferedRide(theID);
    }

    @Override
    @Transactional
    public Seat addNewSeat(SeatSupport seatSupport) {
        return seatDAO.addNewSeat(seatSupport);
    }

    @Override
    @Transactional
    public Response deleteSeatByID(int theID) {
        return seatDAO.deleteSeatByID(theID);
    }

    @Override
    @Transactional
    public List<Seat> getListOfAllSeatsOfSpecificUser(int theID){
        return seatDAO.getListOfAllSeatsOfSpecificUser(theID);
    }
}
