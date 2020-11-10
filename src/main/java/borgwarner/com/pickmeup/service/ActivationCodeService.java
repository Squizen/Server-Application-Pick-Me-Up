package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.support.Response;

import java.util.List;

public interface ActivationCodeService {

    List<ActivationCode> findListOfAllActivationCodes();

    ActivationCode findActivationCodeById(int theID);

    ActivationCode findActivationCodeBySerialNumber(String serialNumber);

    List<ActivationCode> listOfFreeActivationCodes();

    List<ActivationCode> listOfTakenActivationCodes();

    Response addNewActivationCode(ActivationCode activationCode);

    Response deleteActivationCodeById(int theID);

    Response updateActivationCode(ActivationCode activationCode);
}
