package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.ActivationCode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
}
