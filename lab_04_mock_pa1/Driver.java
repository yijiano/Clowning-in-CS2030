public abstract class Driver {
    private final String licensePlate;
    private final int passengerWaitingTime;

    public Driver(String licensePlate, int passengerWaitingTime) {
        this.licensePlate = licensePlate;
        this.passengerWaitingTime = passengerWaitingTime;
    }

    public int getPassengerWaitingTime() {
        return this.passengerWaitingTime;
    }

    protected abstract String getType();

    private int calculateFare(Request request, Service service) {
        request.computeFare(service);
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public String toString() {
        return this.licensePlate + " (" + this.passengerWaitingTime + " mins away)";
    }
}
