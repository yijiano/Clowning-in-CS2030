public class JustRide extends Service {
    private static final int DISTANCE_FARE = 22;
    private static final int PEAK_HOUR_FARE = 500;
    private static final int PEAK_HOUR_START = 600;
    private static final int PEAK_HOUR_END = 900;

    public int computeFare(int distance, int numOfPassengers, int time) {
        return distance * DISTANCE_FARE 
            + ((time >= PEAK_HOUR_START && time <= PEAK_HOUR_END) ? PEAK_HOUR_FARE : 0);
    }

    public String toString() {
        return "JustRide";
    }
}
