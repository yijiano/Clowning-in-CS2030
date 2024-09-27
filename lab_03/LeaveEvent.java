public class LeaveEvent extends Event {
    private final Shop shop;
    private static final int EVENT_PRIORITY = 3;

    LeaveEvent(Customer customer, double eventTime, Shop shop) {
        super(eventTime, customer);
        this.shop = shop;
    }

    protected int getEventPriority() {
        return EVENT_PRIORITY;
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}
