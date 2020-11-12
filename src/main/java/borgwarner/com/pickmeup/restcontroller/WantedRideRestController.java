package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.WantedRideService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
