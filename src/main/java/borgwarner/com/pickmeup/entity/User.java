package borgwarner.com.pickmeup.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import borgwarner.com.pickmeup.jsonhelper.View;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id_user;

    @OneToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "id_activation_code")
    @JsonView({View.User.class})
    private ActivationCode activationCode;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_surname")
    private String user_surname;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "user_password")
    private String user_password;

    @Column(name = "user_phone_number")
    private String user_phone_number;

    @Column(name = "user_car")
    private String user_car;

    @Column(name = "user_description")
    private String user_description;

    @Column(name = "is_active")
    private boolean is_active;

    public User() {

    }

    public User(int id_user, String user_name, String user_surname, String user_email, String user_password,
                String user_phone_number, String user_car, String user_description, boolean is_active) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_phone_number = user_phone_number;
        this.user_car = user_car;
        this.user_description = user_description;
        this.is_active = is_active;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public ActivationCode getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(ActivationCode activationCode) {
        this.activationCode = activationCode;
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
