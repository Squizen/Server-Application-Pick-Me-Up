package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.OfferedRideDAO;
import borgwarner.com.pickmeup.entity.OfferedRide;
import borgwarner.com.pickmeup.support.OfferedRideSupport;
import borgwarner.com.pickmeup.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfferedRideServiceImpl implements OfferedRideService {

    private OfferedRideDAO offeredRideDAO;

    @Autowired
    public OfferedRideServiceImpl(OfferedRideDAO offeredRideDAO){
        this.offeredRideDAO = offeredRideDAO;
    }

    @Override
    @Transactional
    public List<OfferedRide> listOfAllOfferedRides() {
        return offeredRideDAO.findAllOfferedRides();
    }

    @Override
    @Transactional
    public OfferedRide findOfferedRideById(int theID) {
        return offeredRideDAO.findOfferedRideById(theID);
    }

    @Override
    @Transactional
    public List<OfferedRide> listOfRidesToCompany(String from_where, String startingDay, String startingMoment) {
        return offeredRideDAO.listOfRidesToCompany(from_where, startingDay, startingMoment);
    }

    @Override
    @Transactional
    public List<OfferedRide> listOfRidesFromCompany(String to_where, String startingDay, String startingMoment) {
        return offeredRideDAO.listOfRidesFromCompany(to_where, startingDay, startingMoment);
    }

    @Override
    @Transactional
    public Response addNewOfferedRide(OfferedRideSupport offeredRideSupport) {
        return offeredRideDAO.addNewOfferedRide(offeredRideSupport);
    }

    @Override
    @Transactional
    public Response updateOfferedRide(OfferedRideSupport offeredRideSupport) {
        return offeredRideDAO.updateOfferedRide(offeredRideSupport);
    }

    @Override
    @Transactional
    public Response deleteOfferedRideById(int theID) {
        return offeredRideDAO.deleteOfferedRideById(theID);
    }

    @Override
    public List<OfferedRide> getListOfOfferedRidesOfSpecificUser(int theID) {
        return offeredRideDAO.getListOfOfferedRidesOfSpecificUser(theID);
    }
}
