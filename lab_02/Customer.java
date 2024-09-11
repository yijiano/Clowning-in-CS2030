public class Customer {
    private final int id;
    private final double arrivalTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public boolean canBeServed(double time) {
        return this.arrivalTime >= time;
    }

    public double serveTill(double serviceTime) {
        return this.arrivalTime + serviceTime;
    }

    @Override
    public String toString() {
        return ("customer " + this.id);
    }
}
