package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.WantedRideDAO;
import borgwarner.com.pickmeup.entity.WantedRide;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.WantedRideSupport;
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
    @Transactional
    public List<WantedRide> findWantedRidesOfSpecificUser(int theID) {
        return wantedRideDAO.findWantedRidesOfSpecificUser(theID);
    }

    @Override
    @Transactional
    public WantedRide addNewWantedRide(WantedRideSupport wantedRideSupport) {
        return wantedRideDAO.addNewWantedRide(wantedRideSupport);
    }

    @Override
    @Transactional
    public Response updateWantedRide(WantedRideSupport wantedRideSupport) {
        return wantedRideDAO.updateWantedRide(wantedRideSupport);
    }

    @Override
    @Transactional
    public Response deleteWantedRideById(int theID) {
        return wantedRideDAO.deleteWantedRideById(theID);
    }

    @Override
    @Transactional
    public List<WantedRide> findWantedRidesToCompany(String from_where, String startingDay, String startingMoment) {
        return wantedRideDAO.findWantedRidesToCompany(from_where, startingDay, startingMoment);
    }

    @Override
    @Transactional
    public List<WantedRide> findWantedRidesFromCompany(String to_where, String startingDay, String startingMoment) {
        return wantedRideDAO.findWantedRidesFromCompany(to_where, startingDay, startingMoment);
    }
}
