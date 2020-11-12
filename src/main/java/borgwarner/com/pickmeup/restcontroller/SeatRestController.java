package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.Seat;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.SeatService;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.SeatSupport;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatRestController {

    private SeatService seatService;

    @Autowired
    public SeatRestController(SeatService seatService){
        this.seatService = seatService;
    }

    @GetMapping("/seats")
    @JsonView({View.Seat.class})
    public List<Seat> findListOfAllSeats(){
        return seatService.getListOfAllSeats();
    }

    @GetMapping("/seat/{theID}")
    @JsonView({View.Seat.class})
    public Seat findSeatById(@PathVariable int theID){
        return seatService.findSeatById(theID);
    }

    @GetMapping("/seats/offered_ride/{theID}")
    @JsonView({View.Seat.class})
    public List<Seat> findAllSeatsOfSpecificOfferedRide(@PathVariable int theID){
        return seatService.findAllSeatsOfSpecificOfferedRide(theID);
    }
    @PostMapping("/seat/addnew")
    public Response addNewSeat(@RequestBody SeatSupport seatSupport){
        return seatService.addNewSeat(seatSupport);
    }

    @DeleteMapping("/seat/delete/{theID}")
    public Response deleteSeatByID(@PathVariable int theID){
        return seatService.deleteSeatByID(theID);
    }
}
