package borgwarner.com.pickmeup.service;

import borgwarner.com.pickmeup.entity.UserComplaint;
import borgwarner.com.pickmeup.support.UserComplaintSupport;

import java.util.List;

public interface UserComplaintService {

    List<UserComplaint> findAll();

    UserComplaint findUserComplaintByID(int theID);

    UserComplaint addUserComplaint(UserComplaintSupport userComplaintSupport);
}
