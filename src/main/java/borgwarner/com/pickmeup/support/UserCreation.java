package borgwarner.com.pickmeup.support;

public class UserCreation {

    private String serialNumber;
    private String user_name;
    private String user_surname;
    private String user_email;
    private String user_password;
    private String user_phone_number;
    private String user_car;
    private String user_description;
    private boolean is_active;

    public UserCreation() {

    }

    public UserCreation(String serialNumber, String user_name, String user_surname, String user_email, String user_password, String user_phone_number, String user_car, String user_description, boolean is_active) {
        this.serialNumber = serialNumber;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_phone_number = user_phone_number;
        this.user_car = user_car;
        this.user_description = user_description;
        this.is_active = is_active;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getUser_car() {
        return user_car;
    }

    public void setUser_car(String user_car) {
        this.user_car = user_car;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
