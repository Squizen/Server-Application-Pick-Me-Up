package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.UserSupport;

import java.util.List;

public interface UserService {

    User findUserById(int theID);

    List<User> findAllUsers();

    List<User> findAllActiveUsers();

    List<User> findAllInactiveUsers();

    Response addUserWithActivationCodeSerialNumber(UserSupport userSupport);

    Response deleteUserById(int theID);

    Response updateUser(UserSupport userSupport);

    User loginToApplication(String email, String password);

    Response checkIfEmailIsAvailable(String email);
}
