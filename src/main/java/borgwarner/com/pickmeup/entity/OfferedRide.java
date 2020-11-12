package borgwarner.com.pickmeup.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import borgwarner.com.pickmeup.jsonhelper.View;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="offered_ride")
public class OfferedRide {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_offered_ride")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private int id_offered_ride;

    @Column(name="date_of_ride")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private Date date_of_ride;

    @Column(name="time_of_ride")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private Time time_of_ride;

    @Column(name="number_of_free_seats")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private int number_of_free_seats;

    @Column(name="ride_category")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private int ride_category;

    @Column(name="from_where")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private String from_where;

    @Column(name="to_where")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private String to_where;

    @Column(name="user_comment")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private String user_comment;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_user")
    @JsonView({View.OfferedRide.class})
    private User user;

    @OneToMany(mappedBy="offeredRide",
            cascade= {CascadeType.ALL})
//    @JsonView({View.Seat.class, View.OfferedRide.class})
    @JsonView({View.OfferedRide.class})
    private List<Seat> listOfSeats;

    public OfferedRide() {

    }

    public OfferedRide(int id_offered_ride, Date date_of_ride, Time time_of_ride, int number_of_free_seats,
                       int ride_category, String from_where, String to_where, String user_comment) {
        this.id_offered_ride = id_offered_ride;
        this.date_of_ride = date_of_ride;
        this.time_of_ride = time_of_ride;
        this.number_of_free_seats = number_of_free_seats;
        this.ride_category = ride_category;
        this.from_where = from_where;
        this.to_where = to_where;
        this.user_comment = user_comment;
    }

    public int getId_offered_ride() {
        return id_offered_ride;
    }

    public void setId_offered_ride(int id_offered_ride) {
        this.id_offered_ride = id_offered_ride;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getNumber_of_free_seats() {
        return number_of_free_seats;
    }

    public void setNumber_of_free_seats(int number_of_free_seats) {
        this.number_of_free_seats = number_of_free_seats;
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

    public List<Seat> getListOfSeats() {
        return listOfSeats;
    }

    public void setListOfSeats(List<Seat> listOfSeats) {
        this.listOfSeats = listOfSeats;
    }

    public void addSeatToOfferedRide(Seat seat){
        if(listOfSeats == null){
            listOfSeats = new ArrayList<>();
        }
        listOfSeats.add(seat);
    }
}
