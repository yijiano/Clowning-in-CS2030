class WaitEvent extends Event {
    private final Server server;
    private final boolean isRequeue;

    WaitEvent(Customer customer, double eventTime, Server server) {
        super(eventTime, customer);
        this.server = server;
        this.isRequeue = false;
    }

    private WaitEvent(Customer customer, double eventTime,
            Server server, boolean isRequeue) {
        super(eventTime, customer);
        this.server = server;
        this.isRequeue = isRequeue;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        Server updatedServer = shop.getUpdatedServer(this.server);
        if (updatedServer.isBusy()) {
            
            // If server is still busy, create another wait event
            return new Pair<>(new WaitEvent(
                        super.customer, 
                        updatedServer.getNextAvailableTime(),
                        updatedServer,
                        true),
                    shop);
        }

        if (isRequeue) {
            Shop updatedShop = shop.popServerQueue(this.server);
            return new Pair<>(new ServeEvent(
                        super.customer,
                        updatedServer.getNextAvailableTime(),
                        updatedServer),
                    updatedShop);
        }

        // First time waiting
        return new Pair<>(new WaitEvent(
                    super.customer, 
                    updatedServer.getNextAvailableTime(),
                    updatedServer,
                    true),
                shop);
    }

    @Override
    boolean hasNext() {
        return true;
    }

    @Override
    public String toString() {
        return isRequeue ? "" : (super.toString() + " waits at " + this.server);
    }
}

