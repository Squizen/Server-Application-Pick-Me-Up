package borgwarner.com.pickmeup.dao;

import antlr.actions.cpp.ActionLexerTokenTypes;
import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.support.ActivationCodeResponse;
import borgwarner.com.pickmeup.support.Response;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivationCodeDAOHiberImpl implements ActivationCodeDAO {

    private EntityManager entityManager;

    @Autowired
    public ActivationCodeDAOHiberImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<ActivationCode> findAllActivationCodes() {
        Session session = entityManager.unwrap(Session.class);
        List<ActivationCode> listOfAllActivationCodes = session.createQuery("FROM ActivationCode", ActivationCode.class).getResultList();
        return listOfAllActivationCodes;
    }
    @Override
    public ActivationCode findActivationCodeById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        ActivationCode activationCode = session.get(ActivationCode.class, theID);
        return activationCode;
    }
    @Override
    public ActivationCode findActivationCodeBySerialNumber(String serialNumber){
        Session session = entityManager.unwrap(Session.class);
        ActivationCode activationCode = null;
        try{
            activationCode = session.createQuery("FROM ActivationCode ac WHERE ac.serial_number = "  +"\'"+serialNumber+"\'",
                    ActivationCode.class).getSingleResult();
        }catch(NoResultException ex){

        }
        return activationCode;
    }

    @Override
    public List<ActivationCode> findAllFreeActivationCodes() {
        Session session = entityManager.unwrap(Session.class);
        List<ActivationCode> listOfAllActivationCodes = session.createQuery("FROM ActivationCode", ActivationCode.class).getResultList();
        List<ActivationCode> listOfFreeActivationCodes = new ArrayList<>();
        for (ActivationCode activationCode : listOfAllActivationCodes){
            if(activationCode.getUser() == null){
                listOfFreeActivationCodes.add(activationCode);
            }
        }
        return listOfFreeActivationCodes;
    }
    @Override
    public List<ActivationCode> findAllTakenActivationCodes() {
        Session session = entityManager.unwrap(Session.class);
        List<ActivationCode> listOfAllActivationCodes = session.createQuery("FROM ActivationCode", ActivationCode.class).getResultList();
        List<ActivationCode> listOfTakenActivationCodes = new ArrayList<>();
        for (ActivationCode activationCode : listOfAllActivationCodes){
            if(activationCode.getUser() != null){
                listOfTakenActivationCodes.add(activationCode);
            }
        }
        return listOfTakenActivationCodes;
    }

    @Override
    public Response addNewActivationcode(ActivationCode activationCode) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(activationCode);
        return new Response(true, "ActivationCode with ID " + activationCode.getId_activation_code() + " and Serial Number = " + activationCode.getSerial_number()
                + " successfully added");
    }
    @Override
    public Response deleteActivationCodeById(int theID) {
        Session session = entityManager.unwrap(Session.class);
        Response response = new Response();
        ActivationCode activationCode;
        try {
            activationCode = session.get(ActivationCode.class, theID);
        } catch (NoResultException ex) {
            response.setMsg("ActivationCode with ID " + theID + " not found");
            response.setSuccesful(false);
            return response;
        }
        if(activationCode != null){
            Query query = session.createQuery("DELETE FROM ActivationCode activationCode WHERE activationCode.id_activation_code = " + theID);
            query.executeUpdate();
            response.setSuccesful(true);
            response.setMsg("ActivationCode with ID " + theID + " successfully deleted");
        } else {
            response.setMsg("ActivationCode with ID " + theID + " not found");
            response.setSuccesful(false);
        }
        return response;
    }

    @Override
    public Response updateActivationCode(ActivationCode activationCode) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(activationCode);
        return new Response(true, "Activation Code with Serial Number = " + activationCode.getSerial_number() + " has been successfully added to database ");
    }

    @Override
    public ActivationCodeResponse findIfCodeExistsAndItsFree(String serialNumber){
        Session session = entityManager.unwrap(Session.class);
        ActivationCode activationCode = null;
        try{
            activationCode = session.createQuery("FROM ActivationCode ac WHERE ac.serial_number = "  +"\'"+serialNumber+"\'",
                    ActivationCode.class).getSingleResult();
            if(activationCode != null){
                if(activationCode.getUser() == null){
                    return new ActivationCodeResponse(true, "Activation Code exist in database and its free to use", activationCode.getId_activation_code());
                } else {
                    return new ActivationCodeResponse(false, "Activation Code is already taken");
                }
            }
        }catch(NoResultException ex){
            return new ActivationCodeResponse(false, "Activation Code does not exist in database");
        }
        return new ActivationCodeResponse(false, "Activation Code does not exists in database");
    }
}
