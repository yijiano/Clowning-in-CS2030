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
        this.fare = lowestFare(request);
        this.service = lowestFareService(request);
        this.request = request;
    }
    
    private Booking(Driver driver, Request request, Service service) {
        this.driver = driver;
        this.driverType = driver.getType();
        this.fare = calculateFare(request, service);
        this.service = service;
        this.request = request;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    private int calculateFare(Request request, Service service) {
        return request.computeFare(service);
    }

    private int lowestFare(Request request) {      
        return this.driverType.equals("NormalCab")
            ? min(calculateFare(request, new JustRide()), calculateFare(request, new TakeACab()))
            : min(calculateFare(request, new JustRide()), calculateFare(request, new ShareARide()));
    }

    private Service lowestFareService(Request request) {
        return this.driverType.equals("NormalCab")
            ? calculateFare(request, new JustRide()) < calculateFare(request, new TakeACab())
                ? new JustRide()
                : new TakeACab()
            : calculateFare(request, new JustRide()) < calculateFare(request, new ShareARide())
                ? new JustRide()
                : new ShareARide();
    }

    public List<Booking> listAllServices() {
        return this.driverType.equals("NormalCab")
            ? List.of(new Booking(this.driver, this.request, new JustRide()),
                new Booking(this.driver, this.request, new TakeACab()))
            : List.of(new Booking(this.driver, this.request, new JustRide()),
                new Booking(this.driver, this.request, new ShareARide()));
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
