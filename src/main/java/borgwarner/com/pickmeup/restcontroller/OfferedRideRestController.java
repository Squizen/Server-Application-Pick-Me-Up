package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.OfferedRideService;
import borgwarner.com.pickmeup.support.OfferedRideSupport;
import borgwarner.com.pickmeup.support.Response;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferedRideRestController {

    private OfferedRideService offeredRideService;

    @Autowired
    public OfferedRideRestController(OfferedRideService offeredRideService){
        this.offeredRideService = offeredRideService;
    }

    @GetMapping("/offered_rides")
    @JsonView({View.OfferedRide.class})
    public List<OfferedRide> findAllOfferedRides(){
        return offeredRideService.listOfAllOfferedRides();
    }

    @GetMapping("/offered_ride/{theID}")
    @JsonView({View.OfferedRide.class})
    public OfferedRide findOfferedRideById(@PathVariable int theID){
        OfferedRide offeredRide = offeredRideService.findOfferedRideById(theID);
        if(offeredRide != null){
            return offeredRide;
        } else {
            throw new RuntimeException("Offered Ride with ID = " + theID + " not found in database //");
        }
    }
    @GetMapping("/offered_rides/from")
    @JsonView({View.OfferedRide.class})
    @ResponseBody
    public List<OfferedRide> listOfRidesToCompany(@RequestParam String from_where,@RequestParam String startingDay,@RequestParam String startingMoment){
        return offeredRideService.listOfRidesToCompany(from_where, startingDay, startingMoment);
    }
    @GetMapping("/offered_rides/to")
    @JsonView({View.OfferedRide.class})
    @ResponseBody
    public List<OfferedRide> listOfRidesFromCompany(@RequestParam String to_where,@RequestParam String startingDay,@RequestParam String startingMoment){
        return offeredRideService.listOfRidesFromCompany(to_where, startingDay, startingMoment);
    }
    @PostMapping("/offered_ride/addnew")
    public Response addNewOfferedRide(@RequestBody OfferedRideSupport offeredRideSupport){
        offeredRideSupport.setId_offered_ride(0);
        return offeredRideService.addNewOfferedRide(offeredRideSupport);
    }
    @PutMapping("/offered_ride/update")
    public Response updateOfferedRide(@RequestBody OfferedRideSupport offeredRideSupport){
        return offeredRideService.updateOfferedRide(offeredRideSupport);
    }
    @DeleteMapping("/offered_ride/delete/{theID}")
    public Response deleteOfferedRideById(@PathVariable int theID){
        return offeredRideService.deleteOfferedRideById(theID);
    }
    @GetMapping("/offered_rides/user/{theID}")
    @JsonView({View.OfferedRide.class})
    public List<OfferedRide> getListOfOfferedRidesOfSpecificUser(@PathVariable int theID){
        return offeredRideService.getListOfOfferedRidesOfSpecificUser(theID);
    }
}
