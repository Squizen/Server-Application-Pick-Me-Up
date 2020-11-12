package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.WantedRideDAO;
import borgwarner.com.pickmeup.entity.WantedRide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WantedRideServiceImpl implements WantedRideService {

    private WantedRideDAO wantedRideDAO;

    @Autowired
    public WantedRideServiceImpl(WantedRideDAO wantedRideDAO){
       this.wantedRideDAO = wantedRideDAO;
    }

    @Override
    @Transactional
    public List<WantedRide> listOfWantedRide() {
        return wantedRideDAO.findAllWantedRides();
    }

    @Override
    @Transactional
    public WantedRide findWantedRideByID(int theID) {
        return wantedRideDAO.findWantedRideByID(theID);
    }

    @Override
    public List<WantedRide> findWantedRidesOfSpecificUser(int theID) {
        return wantedRideDAO.findWantedRidesOfSpecificUser(theID);
    }
}
