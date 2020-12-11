package borgwarner.com.pickmeup.entity;

import borgwarner.com.pickmeup.jsonhelper.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="user_complaints")
public class UserComplaint {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_complaint")
    @JsonView({View.UserComplaint.class})
    private int id_complaint;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_user")
    @JsonView({View.UserComplaint.class})
    private User id_user;

    @Column(name="complaint")
    @JsonView({View.UserComplaint.class})
    private String complaint;

    @Column(name="time_of_addition")
    @JsonView({View.UserComplaint.class})
    private Time time_of_addition;

    @Column(name="date_of_addition")
    @JsonView({View.UserComplaint.class})
    private Date date_of_addition;

    public UserComplaint(){

    }

    public UserComplaint(int id_complaint, User id_user, String complaint, Time time_of_addition, Date date_of_addition) {
        this.id_complaint = id_complaint;
        this.id_user = id_user;
        this.complaint = complaint;
        this.time_of_addition = time_of_addition;
        this.date_of_addition = date_of_addition;
    }

    public int getId_complaint() {
        return id_complaint;
    }

    public void setId_complaint(int id_complaint) {
        this.id_complaint = id_complaint;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public Time getTime_of_addition() {
        return time_of_addition;
    }

    public void setTime_of_addition(Time time_of_addition) {
        this.time_of_addition = time_of_addition;
    }

    public Date getDate_of_addition() {
        return date_of_addition;
    }

    public void setDate_of_addition(Date date_of_addition) {
        this.date_of_addition = date_of_addition;
    }
}
