package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.ActivationCode;

import java.util.List;

public interface ActivationCodeDAO {

    List<ActivationCode> findAllActivationCodes();
}
