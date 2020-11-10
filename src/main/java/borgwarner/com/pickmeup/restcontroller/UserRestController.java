package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.jsonhelper.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import borgwarner.com.pickmeup.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{theID}")
    @JsonView(View.User.class)
    public User findUserById(@PathVariable int theID){
        User user = userService.findUserById(theID);
        if(user != null){
            return user;
        } else {
            throw new RuntimeException("There is no user with ID = " + theID + " //");
        }
    }

    @GetMapping("/users")
    @JsonView(View.User.class)
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/users/active")
    @JsonView(View.User.class)
    public List<User> findAllActiveUsers(){
        return userService.findAllActiveUsers();
    }

    @GetMapping("/users/inactive")
    @JsonView(View.User.class)
    public List<User> findAllInactiveUsers(){
        return userService.findAllInactiveUsers();
    }
}
