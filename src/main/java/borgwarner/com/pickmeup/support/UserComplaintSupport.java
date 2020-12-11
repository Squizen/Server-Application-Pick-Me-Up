package borgwarner.com.pickmeup.support;

import java.sql.Date;
import java.sql.Time;

public class UserComplaintSupport {

    private int id_complaint;
    private int id_user;
    private String complaint;
    private Time time_of_addition;
    private Date date_of_addition;

    public UserComplaintSupport(){

    }

    public UserComplaintSupport(int id_complaint, int id_user, String complaint, Time time_of_addition, Date date_of_addition) {
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
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
