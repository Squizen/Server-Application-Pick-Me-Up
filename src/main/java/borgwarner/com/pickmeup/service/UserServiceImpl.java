package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.UserDAO;
import borgwarner.com.pickmeup.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }
}
