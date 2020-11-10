package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
}
