package borgwarner.com.pickmeup.support;

public class ActivationCodeResponse extends Response {

    private int id_activation_code;

    public ActivationCodeResponse(boolean successful, String msg){
        super(successful, msg);
    }
    public ActivationCodeResponse(boolean successful, String msg, int id_activation_code){
        super(successful, msg);
        this.id_activation_code = id_activation_code;
    }

    public int getId_activation_code() {
        return id_activation_code;
    }

    public void setId_activation_code(int id_activation_code) {
        this.id_activation_code = id_activation_code;
    }
}
