import java.util.Optional;

public class ServeEvent extends Event {
    private final Shop shop;
    private final Server server;
    private static final int EVENT_PRIORITY = 2;

    ServeEvent(Customer customer, double eventTime, Shop shop, Server server) {
        super(eventTime, customer);
        this.shop = shop;
        this.server = server;
    }

    public Pair<Event, Shop> next(Shop nextShop) {
        return new Pair<Event, Shop>(new DoneEvent(super.customer, this.eventTime), this.shop); 
    }

    protected int getEventPriority() {
        return EVENT_PRIORITY;
    }

    @Override
    public String toString() {
        return super.toString() 
            + " serve by "
            + this.server;
    }
}
