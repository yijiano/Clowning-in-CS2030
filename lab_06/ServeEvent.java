import java.util.function.Supplier;

class ServeEvent extends Event {
    private final Server server;

    ServeEvent(Customer customer, double eventTime, Server server) {
        super(eventTime, customer);
        this.server = server;
    }

    @Override
    double addWaitingTime() {
        return super.eventTime - super.customer.getArrivalTime();
    }

    @Override
    int addNumCustomersServed() {
        return 1;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        double endTime = this.eventTime + shop.getServiceTime();
        Server updatedServer = server.serve(endTime);
        Shop updatedShop = shop.updateServer(updatedServer);

        return new Pair<>(new DoneEvent(customer, endTime, updatedServer), updatedShop);
    }

    @Override
    boolean hasNext() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " serves by " + this.server;
    }
}
