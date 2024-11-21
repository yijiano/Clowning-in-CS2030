import java.util.Optional;

class DoneEvent extends Event {
    private final Server server;

    DoneEvent(Customer customer, double eventTime, Server server) {
        super(eventTime, customer);
        this.server = server.done(eventTime);
    }

    @Override
    Pair<Event, Shop> next(Shop nextShop) {
        return new Pair<>(this, nextShop.updateServer(this.server));
    }

    @Override
    public String toString() {
        return super.toString() + " done";
    }
}
