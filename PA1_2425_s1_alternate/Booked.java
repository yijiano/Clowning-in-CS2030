public class Booked implements Seat {

    Booked() {}

    public boolean isBooked() {
        return true;
    }

    @Override
    public String toString() {
        return "B";
    }
    
}
