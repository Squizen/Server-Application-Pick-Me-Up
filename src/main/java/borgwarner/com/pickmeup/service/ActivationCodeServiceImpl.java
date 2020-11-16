package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.ActivationCodeDAO;
import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.support.ActivationCodeResponse;
import borgwarner.com.pickmeup.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivationCodeServiceImpl implements ActivationCodeService{

    private ActivationCodeDAO activationCodeDAO;

    @Autowired
    public ActivationCodeServiceImpl(ActivationCodeDAO activationCodeDAO){
        this.activationCodeDAO = activationCodeDAO;
    }

    @Override
    @Transactional
    public List<ActivationCode> findListOfAllActivationCodes() {
        return activationCodeDAO.findAllActivationCodes();
    }

    @Override
    @Transactional
    public ActivationCode findActivationCodeById(int theID) {
        return activationCodeDAO.findActivationCodeById(theID);
    }

    @Override
    @Transactional
    public ActivationCode findActivationCodeBySerialNumber(String serialNumber) {
        return activationCodeDAO.findActivationCodeBySerialNumber(serialNumber);
    }
    @Override
    @Transactional
    public List<ActivationCode> listOfFreeActivationCodes() {
        return activationCodeDAO.findAllFreeActivationCodes();
    }
    @Override
    @Transactional
    public List<ActivationCode> listOfTakenActivationCodes() {
        return activationCodeDAO.findAllTakenActivationCodes();
    }

    @Override
    @Transactional
    public Response addNewActivationCode(ActivationCode activationCode) {
        return activationCodeDAO.addNewActivationcode(activationCode);
    }
    @Override
    @Transactional
    public Response deleteActivationCodeById(int theID) {
        return activationCodeDAO.deleteActivationCodeById(theID);
    }

    @Override
    @Transactional
    public Response updateActivationCode(ActivationCode activationCode) {
        return activationCodeDAO.addNewActivationcode(activationCode);
    }

    @Override
    @Transactional
    public ActivationCodeResponse findIfCodeExistsAndItsFree(String serialNumber) {
        return activationCodeDAO.findIfCodeExistsAndItsFree(serialNumber);
    }
}
