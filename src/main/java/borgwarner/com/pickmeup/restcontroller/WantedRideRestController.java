package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.WantedRideService;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.WantedRideSupport;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WantedRideRestController {

    private WantedRideService wantedRideService;

    @Autowired
    public WantedRideRestController(WantedRideService wantedRideService){
        this.wantedRideService = wantedRideService;
    }

    @GetMapping("/wanted_rides")
    @JsonView({View.WantedRide.class})
    public List<WantedRide> listOfWantedRides(){
        return wantedRideService.listOfWantedRide();
    }

    @GetMapping("/wanted_ride/{theID}")
    @JsonView({View.WantedRide.class})
    public WantedRide findWantedRideByID(@PathVariable int theID){
        return wantedRideService.findWantedRideByID(theID);
    }

    @GetMapping("/wanted_rides/user/{theID}")
    @JsonView({View.WantedRide.class})
    public List<WantedRide> findAllWantedRidesOfSpecificUser(@PathVariable int theID){
        return wantedRideService.findWantedRidesOfSpecificUser(theID);
    }

    @PostMapping("/wanted_ride/addnew")
    public Response addNewWantedRide(@RequestBody WantedRideSupport wantedRideSupport){
        return wantedRideService.addNewWantedRide(wantedRideSupport);
    }

    @PutMapping("/wanted_ride/update")
    public Response updateWantedRide(@RequestBody WantedRideSupport wantedRideSupport){
        return wantedRideService.updateWantedRide(wantedRideSupport);
    }
    @DeleteMapping("/wanted_ride/delete/{theID}")
    public Response deleteWantedRideById(@PathVariable int theID){
        return wantedRideService.deleteWantedRideById(theID);
    }
    @GetMapping("/wanted_rides/from")
    @JsonView({View.WantedRide.class})
    @ResponseBody
    public List<WantedRide> findWantedRidesToCompany(@RequestParam String from_where, @RequestParam String startingDay, @RequestParam String startingMoment){
        return wantedRideService.findWantedRidesToCompany(from_where, startingDay, startingMoment);
    }
    @GetMapping("/wanted_rides/to")
    @JsonView({View.WantedRide.class})
    @ResponseBody
    public List<WantedRide> findWantedRidesFromCompany(@RequestParam String to_where, @RequestParam String startingDay, @RequestParam String startingMoment){
        return wantedRideService.findWantedRidesFromCompany(to_where, startingDay, startingMoment);
    }
}
