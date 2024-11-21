class Event implements Comparable<Event> {
    protected final double eventTime;
    protected final Customer customer;

    Event(double eventTime, Customer customer) {
        this.eventTime = eventTime;
        this.customer = customer;
    }

    Pair<Event, Shop> next(Shop nextShop) {
        return new Pair<>(this, nextShop);
    }

    boolean hasNext() {
        return false;
    }

    double addWaitingTime() {
        return 0.000;
    }

    int addNumCustomersServed() {
        return 0;
    }

    int addNumCustomersLeft() {
        return 0;
    }

    @Override
    public int compareTo(Event otherEvent) {
        if (this.eventTime - otherEvent.eventTime != 0) {
            return this.eventTime - otherEvent.eventTime > 0 ? 1 : -1;
        }
        return this.customer.compareTo(otherEvent.customer);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + this.customer;
    }
}
