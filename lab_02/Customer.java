public class Customer {
    private final int id;
    private final double arrival_time;

    Customer(int id, double arrival_time) {
        this.id = id;
        this.arrival_time = arrival_time;
    }

    public boolean canBeServed(double time) {
        return this.arrival_time >= time;
    }

    public double serveTill (double service_time) {
        return this.arrival_time + service_time;
    }

    @Override
    public String toString() {
        return ("customer " + this.id);
    }
}
