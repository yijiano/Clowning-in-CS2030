import java.util.List;

public class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final String driverType;
    private final int fare;
    private final Service service;
    private final Request request;
    
    public Booking(Driver driver, Request request) {
        this.driver = driver;
        this.driverType = driver.getType();
        this.fare = driver.lowestFare(request);
        this.service = driver.lowestFareService(request);
        this.request = request;
    }
    
    private Booking(Driver driver, Request request, Service service) {
        this.driver = driver;
        this.driverType = driver.getType();
        this.fare = driver.calculateFare(request, service);
        this.service = service;
        this.request = request;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public List<Booking> listAllServices() {
        return this.driver.getServices()
            .stream()
            .map(service -> new Booking(this.driver, this.request, service))
            .toList();
    }

    @Override
    public int compareTo(Booking other) {
        if (this.fare == other.fare) {
            return this.driver.getPassengerWaitingTime()
                - other.driver.getPassengerWaitingTime();
        }
        return this.fare - other.fare;    
    }

    @Override
    public String toString() {
        return "$" + (this.fare / 100) + "."
            + (this.fare % 100 == 0 ? "00" : this.fare % 100)
            + " using " + this.driver + " (" + this.service + ")";
    }
}
