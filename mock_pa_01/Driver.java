import java.util.List;

public abstract class Driver {
    private final String licensePlate;
    private final int passengerWaitingTime;

    public Driver(String licensePlate, int passengerWaitingTime) {
        this.licensePlate = licensePlate;
        this.passengerWaitingTime = passengerWaitingTime;
    }

    protected int getPassengerWaitingTime() {
        return this.passengerWaitingTime;
    }

    protected int min(int a, int b) {
        return a < b ? a : b;
    }

    protected int calculateFare(Request request, Service service) {
        return request.computeFare(service);
    }

    protected abstract String getType();

    protected abstract int lowestFare(Request request);

    protected abstract Service lowestFareService(Request request);

    protected abstract List<Service> getServices();

    public String toString() {
        return this.licensePlate + " (" + this.passengerWaitingTime + " mins away)";
    }
}
