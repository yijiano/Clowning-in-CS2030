public class PrivateCar extends Driver {
    public PrivateCar(String licensePlate, int passengerWaitingTime) {
        super(licensePlate, passengerWaitingTime);
    }

    protected String getType() {
        return "PrivateCar";
    }

    @Override
    public String toString() {
        return super.toString() + " PrivateCar";
    }
}
