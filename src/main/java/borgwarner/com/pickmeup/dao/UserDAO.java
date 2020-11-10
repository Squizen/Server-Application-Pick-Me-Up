package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.User;
import java.util.List;

public interface UserDAO {

    List<User> findAllUsers();
}
