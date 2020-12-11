package borgwarner.com.pickmeup.entity;

import borgwarner.com.pickmeup.jsonhelper.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="wanted_ride")
public class WantedRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_wanted_ride")
    @JsonView({View.User.class, View.WantedRide.class})
    private int id_wanted_ride;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_user")
    @JsonView({View.WantedRide.class})
    private User user;

    @Column(name="user_phone_number")
    @JsonView({View.User.class, View.WantedRide.class})
    private String user_phone_number;

    @Column(name="date_of_ride")
    @JsonView({View.User.class, View.WantedRide.class})
    private Date date_of_ride;

    @Column(name="time_of_ride")
    @JsonView({View.User.class, View.WantedRide.class})
    private Time time_of_ride;

    @Column(name="ride_category")
    @JsonView({View.User.class, View.WantedRide.class})
    private int ride_category;

    @Column(name="from_where")
    @JsonView({View.User.class, View.WantedRide.class})
    private String from_where;

    @Column(name="to_where")
    @JsonView({View.User.class, View.WantedRide.class})
    private String to_where;

    @Column(name="user_comment")
    @JsonView({View.User.class, View.WantedRide.class})
    private String user_comment;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_user_driver")
    @JsonView({View.WantedRide.class})
    private User id_user_driver;

    public WantedRide(){

    }

    public int getId_wanted_ride() {
        return id_wanted_ride;
    }

    public void setId_wanted_ride(int id_wanted_ride) {
        this.id_wanted_ride = id_wanted_ride;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public Date getDate_of_ride() {
        return date_of_ride;
    }

    public void setDate_of_ride(Date date_of_ride) {
        this.date_of_ride = date_of_ride;
    }

    public Time getTime_of_ride() {
        return time_of_ride;
    }

    public void setTime_of_ride(Time time_of_ride) {
        this.time_of_ride = time_of_ride;
    }

    public int getRide_category() {
        return ride_category;
    }

    public void setRide_category(int ride_category) {
        this.ride_category = ride_category;
    }

    public String getFrom_where() {
        return from_where;
    }

    public void setFrom_where(String from_where) {
        this.from_where = from_where;
    }

    public String getTo_where() {
        return to_where;
    }

    public void setTo_where(String to_where) {
        this.to_where = to_where;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public User getId_user_driver() {
        return id_user_driver;
    }

    public void setId_user_driver(User id_user_driver) {
        this.id_user_driver = id_user_driver;
    }
}
