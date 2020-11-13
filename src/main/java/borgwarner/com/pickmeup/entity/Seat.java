package borgwarner.com.pickmeup.entity;

import borgwarner.com.pickmeup.jsonhelper.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_seat")
    @JsonView({View.User.class, View.ActivationCode.class, View.OfferedRide.class, View.Seat.class})
    private int id_seat;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name="id_offered_ride")
    @JsonView({View.Seat.class, View.User.class})
    private OfferedRide offeredRide;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_user")
    @JsonView({View.Seat.class, View.OfferedRide.class})
    private User user;

    public Seat(){

    }

    public int getId_seat() {
        return id_seat;
    }

    public void setId_seat(int id_seat) {
        this.id_seat = id_seat;
    }

    public OfferedRide getOfferedRide() {
        return offeredRide;
    }

    public void setOfferedRide(OfferedRide offeredRide) {
        this.offeredRide = offeredRide;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
