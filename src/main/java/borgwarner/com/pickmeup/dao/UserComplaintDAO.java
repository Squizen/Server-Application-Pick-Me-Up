package borgwarner.com.pickmeup.dao;

import borgwarner.com.pickmeup.entity.UserComplaint;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.support.UserComplaintSupport;

import java.util.List;

public interface UserComplaintDAO {

    List<UserComplaint> findAll();

    UserComplaint findUserComplaintById(int theID);

    UserComplaint addUserComplaint(UserComplaintSupport userComplaintSupport);
}
