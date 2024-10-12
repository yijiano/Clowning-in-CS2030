public class Available implements Seat {
    
    Available() {}

    public boolean isBooked() {
        return false;
    }

    @Override
    public String toString() {
        return ".";
    }
}
