package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.dao.UserComplaintDAO;
import borgwarner.com.pickmeup.entity.UserComplaint;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.support.UserComplaintSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserComplaintServiceImpl implements UserComplaintService {

    private UserComplaintDAO userComplaintDAO;

    @Autowired
    public UserComplaintServiceImpl(UserComplaintDAO userComplaintDAO){
        this.userComplaintDAO = userComplaintDAO;
    }

    @Override
    @Transactional
    public List<UserComplaint> findAll() {
        return userComplaintDAO.findAll();
    }
    @Override
    @Transactional
    public UserComplaint findUserComplaintByID(int theID) {
        return userComplaintDAO.findUserComplaintById(theID);
    }

    @Override
    @Transactional
    public UserComplaint addUserComplaint(UserComplaintSupport userComplaintSupport) {
        return userComplaintDAO.addUserComplaint(userComplaintSupport);
    }
}
