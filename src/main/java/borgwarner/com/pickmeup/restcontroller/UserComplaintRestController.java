package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.UserComplaint;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.UserComplaintService;
import borgwarner.com.pickmeup.support.UserComplaintSupport;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserComplaintRestController {

    @Autowired
    private UserComplaintService userComplaintService;

    public UserComplaintRestController(UserComplaintService userComplaintService){
        this.userComplaintService = userComplaintService;
    }

    @GetMapping("/user_complaints")
    @JsonView({View.UserComplaint.class})
    public List<UserComplaint> listOfUserComplaints(){
        return userComplaintService.findAll();
    }
    @GetMapping("/user_complaint/{theID}")
    @JsonView({View.UserComplaint.class})
    public UserComplaint getUserComplaintByID(@PathVariable int theID){
        return userComplaintService.findUserComplaintByID(theID);
    }
    @PostMapping("/user_complaint/add")
    @JsonView({View.UserComplaint.class})
    public UserComplaint addUserComplaint(@RequestBody UserComplaintSupport userComplaintSupport){
        return userComplaintService.addUserComplaint(userComplaintSupport);
    }
}
