package borgwarner.com.pickmeup.restcontroller;

import borgwarner.com.pickmeup.entity.ActivationCode;
import borgwarner.com.pickmeup.jsonhelper.View;
import borgwarner.com.pickmeup.service.ActivationCodeService;
import borgwarner.com.pickmeup.support.ActivationCodeResponse;
import borgwarner.com.pickmeup.support.Response;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/activationcode/{theID}")
    @JsonView({View.ActivationCode.class})
    public ActivationCode findActivationCodeById(@PathVariable int theID){
        ActivationCode activationCode = activationCodeService.findActivationCodeById(theID);
        if(activationCode != null){
            return activationCode;
        } else {
            throw new RuntimeException("There is no ActivationCode with ID = " + theID + " //");
        }
    }

    @GetMapping("/activationcode")
    @ResponseBody
    @JsonView({View.ActivationCode.class})
    public ActivationCode findActivationCodeBySerialNumber(@RequestParam String serialNumber){
        ActivationCode activationCode = activationCodeService.findActivationCodeBySerialNumber(serialNumber);
        if(activationCode != null){
            return activationCode;
        } else {
            throw new RuntimeException("There is no ActivationCode with Serial Number = " + serialNumber + " //");
        }
    }
    @GetMapping("/activationcodes/free")
    @JsonView({View.ActivationCode.class})
    public List<ActivationCode> listOfFreeActivationCodes(){
        return activationCodeService.listOfFreeActivationCodes();
    }
    @GetMapping("/activationcodes/taken")
    @JsonView({View.ActivationCode.class})
    public List<ActivationCode> listOfTakenActivationCodes(){
        return activationCodeService.listOfTakenActivationCodes();
    }
    @PostMapping("/activationcodes/addnew")
    public Response addNewActivationCode(@RequestBody ActivationCode activationCode){
        activationCode.setId_activation_code(0);
        return activationCodeService.addNewActivationCode(activationCode);
    }
    @DeleteMapping("/activationcodes/delete/{theID}")
    public Response deleteActivationCodeById(@PathVariable int theID){
        return activationCodeService.deleteActivationCodeById(theID);
    }
    @PutMapping("/activationcode/update")
    public Response updateActivationCode(@RequestBody ActivationCode activationCode){
        return activationCodeService.updateActivationCode(activationCode);
    }
    @GetMapping("/activationcode/check")
    @ResponseBody
    public ActivationCodeResponse findIfCodeExistsAndItsFree(@RequestParam String serialNumber){
        return activationCodeService.findIfCodeExistsAndItsFree(serialNumber);
    }
}
