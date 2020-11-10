package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.support.Response;

import java.util.List;

public interface ActivationCodeDAO {

    List<ActivationCode> findAllActivationCodes();

    ActivationCode findActivationCodeById(int theID);

    ActivationCode findActivationCodeBySerialNumber(String serialNumber);

    List<ActivationCode> findAllFreeActivationCodes();

    List<ActivationCode> findAllTakenActivationCodes();

    Response addNewActivationcode(ActivationCode activationCode);

    Response deleteActivationCodeById(int theID);

    Response updateActivationCode(ActivationCode activationCode);
}
