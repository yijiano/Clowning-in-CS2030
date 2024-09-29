import java.util.List;

public class PrivateCar extends Driver {
    public PrivateCar(String licensePlate, int passengerWaitingTime) {
        super(licensePlate, passengerWaitingTime);
    }

    protected String getType() {
        return "PrivateCar";
    }

    protected List<Service> getServices() {
        return List.of(new JustRide(), new ShareARide());
    }

    protected int lowestFare(Request request) {
        return super.min(super.calculateFare(request, new JustRide()), 
            super.calculateFare(request, new ShareARide()));
    }

    protected Service lowestFareService(Request request) {
        int fareJustRide = super.calculateFare(request, new JustRide());
        int fareShareARide = super.calculateFare(request, new ShareARide());
        return fareJustRide < fareShareARide ? new JustRide() : new ShareARide();
    }

    @Override
    public String toString() {
        return super.toString() + " PrivateCar";
    }
}
