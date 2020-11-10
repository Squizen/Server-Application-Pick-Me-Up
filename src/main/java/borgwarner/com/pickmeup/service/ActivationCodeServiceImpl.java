package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.ActivationCodeDAO;
import borgwarner.com.pickmeup.entity.ActivationCode;
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
}
