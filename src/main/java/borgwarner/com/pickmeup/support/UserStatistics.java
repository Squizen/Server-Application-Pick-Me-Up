package borgwarner.com.pickmeup.support;

public class UserStatistics {

    private int offeredRides;
    private int passengersTransported;
    private int ridesAsPassenger;

    public UserStatistics(){

    }

    public int getOfferedRides() {
        return offeredRides;
    }

    public void setOfferedRides(int offeredRides) {
        this.offeredRides = offeredRides;
    }

    public int getPassengersTransported() {
        return passengersTransported;
    }

    public void setPassengersTransported(int passengersTransported) {
        this.passengersTransported = passengersTransported;
    }

    public int getRidesAsPassenger() {
        return ridesAsPassenger;
    }

    public void setRidesAsPassenger(int ridesAsPassenger) {
        this.ridesAsPassenger = ridesAsPassenger;
    }
}
