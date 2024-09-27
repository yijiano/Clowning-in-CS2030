public class NormalCab extends Driver {
    public NormalCab(String licensePlate, int passengerWaitingTime) {
        super(licensePlate, passengerWaitingTime);
    }

    protected String getType() {
        return "NormalCab";
    }

    @Override
    public String toString() {
        return super.toString() + " NormalCab";
    }
}
