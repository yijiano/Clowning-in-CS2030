class Customer implements Comparable<Customer> {
    private final int id;
    private final double arrivalTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    int getId() {
        return this.id;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    @Override
    public int compareTo(Customer other) {
        if (this == other) {
            return 0;
        }
        int compareTime = Double.compare(this.arrivalTime, other.arrivalTime);
        if (compareTime != 0) {
            return compareTime;
        }
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "customer " + this.id;
    }
}
