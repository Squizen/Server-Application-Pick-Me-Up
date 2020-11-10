package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.ActivationCodeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivationCodeRestController {

    private ActivationCodeService activationCodeService;

    @Autowired
    public ActivationCodeRestController(ActivationCodeService activationCodeService){
        this.activationCodeService = activationCodeService;
    }

    @GetMapping("/activationcodes")
    @JsonView({View.ActivationCode.class})
    public List<ActivationCode> findAllActivationCodes(){
        return activationCodeService.findListOfAllActivationCodes();
    }
}
