package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.UserDAO;
import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.UserStatistics;
import borgwarner.com.pickmeup.support.UserSupport;
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
    public User findUserById(int theID) {
        return userDAO.findUserById(theID);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    @Transactional
    public List<User> findAllActiveUsers() {
        return userDAO.findAllActiveUsers();
    }

    @Override
    @Transactional
    public List<User> findAllInactiveUsers() {
        return userDAO.findAllInactiveUsers();
    }

    @Override
    @Transactional
    public User addUserWithActivationCodeSerialNumber(UserSupport userSupport) {
        return userDAO.addUserWithActivationCodeSerialNumber(userSupport);
    }

    @Override
    @Transactional
    public Response deleteUserById(int theID) {
        return userDAO.deleteUserById(theID);
    }

    @Override
    @Transactional
    public Response updateUser(UserSupport userSupport) {
        return userDAO.updateUser(userSupport);
    }

    @Override
    @Transactional
    public User loginToApplication(String email, String password) {
        return userDAO.loginToApplication(email, password);
    }

    @Override
    @Transactional
    public Response checkIfEmailIsAvailable(String email) {
        return userDAO.checkIfEmailIsAvailable(email);
    }

    @Override
    @Transactional
    public UserStatistics requestUserStatistics(int userID) {
        return userDAO.requestUserStatistics(userID);
    }
}
