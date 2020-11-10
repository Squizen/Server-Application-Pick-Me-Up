package borgwarner.com.pickmeup.entity;

import borgwarner.com.pickmeup.jsonhelper.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="activation_code")
public class ActivationCode {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_activation_code")
    @JsonView({View.ActivationCode.class, View.User.class})
    private int id_activation_code;

    @Column(name="serial_number")
    @JsonView({View.ActivationCode.class, View.User.class})
    private String serial_number;

    @OneToOne(mappedBy="activationCode", cascade= {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JsonView({View.ActivationCode.class})
    private User user;

    public ActivationCode() {

    }

    public ActivationCode(int id_activation_code, String serial_number) {
        this.id_activation_code = id_activation_code;
        this.serial_number = serial_number;
    }

    public int getId_activation_code() {
        return id_activation_code;
    }

    public void setId_activation_code(int id_activation_code) {
        this.id_activation_code = id_activation_code;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ActivationCode [id_activation_code=" + id_activation_code + ", serial_number=" + serial_number + "]";
    }
}

