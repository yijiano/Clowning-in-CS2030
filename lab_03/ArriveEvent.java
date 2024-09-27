import java.util.Optional;

public class ArriveEvent extends Event {
    private static final int EVENT_PRIORITY = 1;

    ArriveEvent(Customer customer, double eventTime) {
        super(eventTime, customer);
    }

    private Event createLeaveEvent(Shop shop) {
        return new LeaveEvent(super.customer, this.eventTime, shop);
    }
    
    private Event createServeEvent(Server server, Shop shop) {
        return new ServeEvent(super.customer, this.eventTime, shop, server);
    }
    
    protected int getEventPriority() {
        return EVENT_PRIORITY;
    }

    @Override
    public Pair<Event, Shop> next(Shop nextShop) {
        Optional<Server> newServer = nextShop.findServer(super.customer);
        
        Shop updatedShop = newServer
            .map(x -> nextShop.update(x.serve(super.customer)))
            .orElse(nextShop);
        
        Event nextEvent = newServer
                .map(x -> createServeEvent(x, updatedShop))
                    .orElseGet(() -> createLeaveEvent(updatedShop));

        return new Pair<>(nextEvent, updatedShop);
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
