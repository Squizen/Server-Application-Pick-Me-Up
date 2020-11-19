package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.entity.Seat;
import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.SeatSupport;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatDAOHiberImpl implements SeatDAO {

    private EntityManager entityManager;

    @Autowired
    public SeatDAOHiberImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Seat> getListOfAllSeats() {
        Session session = entityManager.unwrap(Session.class);
        List<Seat> listOfAllSeats = session.createQuery("FROM Seat", Seat.class).getResultList();
        return listOfAllSeats;
    }

    @Override
    public Seat findSeatById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        Seat seat;
        try{
            seat = session.get(Seat.class, theID);
            return seat;
        }catch(NoResultException nre){
            throw new RuntimeException("Seat with ID = " + theID + " not exist in database");
        }
    }

    @Override
    public List<Seat> findAllSeatsOfSpecificOfferedRide(int theID) {
        List<Seat> listOfAllSeats = getListOfAllSeats();
        List<Seat> listOfSeatsOfSpecificRides = new ArrayList<>();
        for (Seat seat : listOfAllSeats){
            if(seat.getOfferedRide().getId_offered_ride() == theID){
                listOfSeatsOfSpecificRides.add(seat);
            }
        }
        return listOfSeatsOfSpecificRides;
    }

    @Override
    public Seat addNewSeat(SeatSupport seatSupport) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, seatSupport.getId_user());
        OfferedRide offeredRide = session.get(OfferedRide.class, seatSupport.getId_offered_ride());
        if(user != null && offeredRide != null) {
            if(offeredRide.getNumber_of_free_seats() > 0){
                offeredRide.setNumber_of_free_seats(offeredRide.getNumber_of_free_seats() - 1 );
                Seat seat = new Seat();
                seat.setId_seat(0);
                seat.setOfferedRide(offeredRide);
                seat.setUser(user);
                offeredRide.addSeatToOfferedRide(seat);
                user.addToListOfUserSeats(seat);
                session.saveOrUpdate(seat);
                session.saveOrUpdate(user);
                session.saveOrUpdate(offeredRide);
                return seat;
            } else {
                throw new RuntimeException("There are no more free seats for this ride");
            }
        } else {
            throw new RuntimeException("User or OfferedRide does not exists");
        }
    }

    @Override
    public Response deleteSeatByID(int theID) {
        Session session = entityManager.unwrap(Session.class);
        Seat seat = null;
        try{
            seat = session.get(Seat.class, theID);
        }catch(NoResultException nre){

        }
        if(seat != null){
            seat.getOfferedRide().setNumber_of_free_seats(seat.getOfferedRide().getNumber_of_free_seats() + 1);
            session.createQuery("DELETE FROM Seat seat WHERE seat.id_seat = " + theID).executeUpdate();
            return new Response(true, "Seat has been successfully deleted from database ");
        } else {
            return new Response(false, "Seat with ID = " + theID + " not exist in database");
        }
    }
}
