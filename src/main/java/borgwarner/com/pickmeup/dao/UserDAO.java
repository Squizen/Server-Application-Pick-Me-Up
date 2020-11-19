package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.UserSupport;
import borgwarner.com.pickmeup.entity.User;
import java.util.List;

public interface UserDAO {

    User findUserById(int theID);

    List<User> findAllUsers();

    List<User> findAllActiveUsers();

    List<User> findAllInactiveUsers();

    User addUserWithActivationCodeSerialNumber(UserSupport userSupport);

    Response deleteUserById(int theID);

    Response updateUser(UserSupport userSupport);

    User loginToApplication(String email, String password);

    Response checkIfEmailIsAvailable(String email);
}
