package borgwarner.com.pickmeup.support;

public class ActivationCodeResponse extends Response {

    private int id_activation_code;

    public ActivationCodeResponse(boolean successfull, String msg){
        super(successfull, msg);
    }
    public ActivationCodeResponse(boolean successfull, String msg, int id_activation_code){
        super(successfull, msg);
        this.id_activation_code = id_activation_code;
    }

    public int getId_activation_code() {
        return id_activation_code;
    }

    public void setId_activation_code(int id_activation_code) {
        this.id_activation_code = id_activation_code;
    }
}
