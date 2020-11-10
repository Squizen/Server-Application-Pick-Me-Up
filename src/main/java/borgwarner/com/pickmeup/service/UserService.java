package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(int theID);

    List<User> findAllUsers();

    List<User> findAllActiveUsers();

    List<User> findAllInactiveUsers();
}
