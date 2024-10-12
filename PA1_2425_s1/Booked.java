public class Booked implements Seat {
    @Override
    public boolean isBooked() {
        return true;
    }

    @Override
    public String toString() {
        return "B";
    }
}
