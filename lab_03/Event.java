public abstract class Event implements Comparable<Event> {
    protected final double eventTime;
    protected final Customer customer;

    Event(double eventTime, Customer customer) {
        this.eventTime = eventTime;
        this.customer = customer;
    }

    public int compareTo(Event otherEvent) {
        if (this.eventTime - otherEvent.eventTime != 0) {
            return this.eventTime - otherEvent.eventTime > 0 ? 1 : -1;
        }

        // lower EVENT_PRIORITY displayed first
        return this.getEventPriority() == otherEvent.getEventPriority()
            ? this.customer.getId() - otherEvent.customer.getId()
            : this.getEventPriority() - otherEvent.getEventPriority();
    }

    protected boolean hasNext() {
        return this.toString().contains("serve") || this.toString().contains("arrive");
    }

    public Pair<Event, Shop> next(Shop nextShop) {
        return new Pair<>(this, nextShop);
    }

    protected abstract int getEventPriority();

    public String toString() {
        return this.eventTime + " " + this.customer;
    }
}
