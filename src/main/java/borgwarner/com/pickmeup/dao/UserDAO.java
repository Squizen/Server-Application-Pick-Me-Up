package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.support.UserCreation;
import borgwarner.com.pickmeup.entity.User;
import java.util.List;

public interface UserDAO {

    User findUserById(int theID);

    List<User> findAllUsers();

    List<User> findAllActiveUsers();

    List<User> findAllInactiveUsers();

    User addUserWithActivationCodeSerialNumber(UserCreation userCreation);
}
