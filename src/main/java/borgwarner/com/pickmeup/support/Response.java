package borgwarner.com.pickmeup.support;

public class Response {

    private boolean succesful;
    private String msg;

    public Response() {
    }

    public Response(boolean succesful, String msg) {
        this.succesful = succesful;
        this.msg = msg;
    }

    public boolean isSuccesful() {
        return succesful;
    }

    public void setSuccesful(boolean succesful) {
        this.succesful = succesful;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
