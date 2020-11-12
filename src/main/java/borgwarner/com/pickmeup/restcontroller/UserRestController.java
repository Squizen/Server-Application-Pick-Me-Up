package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.User;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.support.Response;
import borgwarner.com.pickmeup.support.UserSupport;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/user/addnew")
    public Response addUserWithActivationCodeSerialNumber(@RequestBody UserSupport userSupport){
        return userService.addUserWithActivationCodeSerialNumber(userSupport);
    }
    @DeleteMapping("/user/delete/{theID}")
    public Response deleteUserById(@PathVariable int theID){
        return userService.deleteUserById(theID);
    }
    @PutMapping("/user/update")
    public Response updateUser(@RequestBody UserSupport userSupport){
        return userService.updateUser(userSupport);
    }
    @GetMapping("/login")
    @JsonView(View.User.class)
    public User loginToApplication(@RequestParam String email, @RequestParam String password){
        return userService.loginToApplication(email, password);
    }
}
