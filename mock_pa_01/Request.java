public class Request {
    private final int distance;
    private final int numOfPassengers;
    private final int time;

    public Request(int distance, int numOfPassengers, int time) {
        this.distance = distance;
        this.numOfPassengers = numOfPassengers;
        this.time = time;
    }

    public int computeFare(Service service) {
        return service.computeFare(this.distance, this.numOfPassengers, this.time);
    }

    public String toString() {
        return this.distance + "km for " + this.numOfPassengers + "pax @ " + this.time + "hrs";
    }
}
