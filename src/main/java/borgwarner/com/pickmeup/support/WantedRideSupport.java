package borgwarner.com.pickmeup.support;

import java.sql.Date;
import java.sql.Time;

public class WantedRideSupport {

    private int id_wanted_ride;
    private int id_user;
    private String user_phone_number;
    private Date date_of_ride;
    private Time time_of_ride;
    private int ride_category;
    private String from_where;
    private String to_where;
    private String user_comment;
    private int id_user_driver;

    public WantedRideSupport(){

    }

    public int getId_wanted_ride() {
        return id_wanted_ride;
    }

    public void setId_wanted_ride(int id_wanted_ride) {
        this.id_wanted_ride = id_wanted_ride;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public int getId_user_driver() {
        return id_user_driver;
    }

    public void setId_user_driver(int id_user_driver) {
        this.id_user_driver = id_user_driver;
    }
}
