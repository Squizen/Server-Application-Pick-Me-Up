package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.ActivationCode;

import java.util.List;

public interface ActivationCodeService {

    List<ActivationCode> findListOfAllActivationCodes();
}
