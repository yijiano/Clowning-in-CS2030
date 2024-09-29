public class TakeACab extends Service {
    private static final int BOOKING_FEE = 200;
    private static final int DISTANCE_FARE = 33;

    public int computeFare(int distance, int numOfPassengers, int time) {
        return BOOKING_FEE + distance * DISTANCE_FARE;
    }

    public String toString() {
        return "TakeACab";
    }
}
