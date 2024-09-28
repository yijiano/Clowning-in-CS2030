import java.util.List;

public class NormalCab extends Driver {
    public NormalCab(String licensePlate, int passengerWaitingTime) {
        super(licensePlate, passengerWaitingTime);
    }

    protected String getType() {
        return "NormalCab";
    }

    protected List<Service> getServices() {
        return List.of(new JustRide(), new TakeACab());
    }

    protected int lowestFare(Request request) {
        return super.min(super.calculateFare(request, new JustRide()), 
            super.calculateFare(request, new TakeACab()));
    }

    protected Service lowestFareService(Request request) {
        int fareJustRide = super.calculateFare(request, new JustRide());
        int fareTakeACab = super.calculateFare(request, new TakeACab());
        return fareJustRide < fareTakeACab ? new JustRide() : new TakeACab();
    }

    @Override
    public String toString() {
        return super.toString() + " NormalCab";
    }
}
