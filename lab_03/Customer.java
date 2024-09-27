public class Customer {
    private final int id;
    private final double arrivalTime;
    private final double serviceTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = 1.0;
    }

    Customer(int id, double arrivalTime, double serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public boolean canBeServed(double time) {
        return this.arrivalTime >= time;
    }

    public double serveTill(double serviceTime) {
        return this.arrivalTime + serviceTime;
    }

    protected double getServiceTime() {
        return this.serviceTime;
    }

    protected int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return ("customer " + this.id);
    }
}
