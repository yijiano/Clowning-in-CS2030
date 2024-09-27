import java.util.Optional;

public class DoneEvent extends Event {
    private static final int EVENT_PRIORITY = 0;

    DoneEvent(Customer customer, double eventTime) {
        super(customer.serveTill(customer.getServiceTime()), customer);
    }

    protected int getEventPriority() {
        return EVENT_PRIORITY;
    }

    @Override
    public String toString() {
        return super.toString() + " done";
    }
}
