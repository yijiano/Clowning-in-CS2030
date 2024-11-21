import java.util.Optional;

class ArriveEvent extends Event {
    ArriveEvent(Customer customer, double eventTime) {
        super(eventTime, customer);
    }

    private Event createServeEvent(Server server) {
        return new ServeEvent(super.customer, super.eventTime, server);
    }

    private Event createWaitEvent(Server server) {
        return new WaitEvent(super.customer, super.eventTime, server);
    }

    private Event createLeaveEvent() {
        return new LeaveEvent(super.customer, super.eventTime);
    }

    @Override
    boolean hasNext() {
        return true;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        return shop.findServer(super.customer)
            .map(server -> {
                if (server.canServe(super.customer)) {
                    return new Pair<>(createServeEvent(server), shop);
                } else {
                    Shop updatedShop = shop.pushServerQueue(server);
                    return new Pair<>(createWaitEvent(server), updatedShop);
                }
            })
        .orElse(new Pair<>(createLeaveEvent(), shop));
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
