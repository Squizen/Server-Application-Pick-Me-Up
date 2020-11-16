package borgwarner.com.pickmeup.support;

public class Response {

    private boolean successful;
    private String msg;

    public Response() {
    }

    public Response(boolean successful, String msg) {
        this.successful = successful;
        this.msg = msg;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccesful(boolean successful) {
        this.successful = successful;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
