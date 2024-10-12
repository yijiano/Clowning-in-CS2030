public class Available implements Seat {
    @Override
    public boolean isBooked() {
        return false;
    }

    @Override
    public String toString() {
        return ".";
    }
}
