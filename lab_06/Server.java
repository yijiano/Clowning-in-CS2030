class Server {
    private final int id;
    private final double nextAvailableTime;
    private final boolean isBusy;

    Server(int id) {
        this.id = id;
        this.nextAvailableTime = 0.0;
        this.isBusy = false;
    }

    private Server(int id, double nextAvailableTime, boolean isBusy) {
        this.id = id;
        this.nextAvailableTime = nextAvailableTime;
        this.isBusy = isBusy;
    }

    int getId() {
        return this.id;
    }

    boolean isBusy() {
        return isBusy;
    }

    double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    boolean canServe(Customer customer) {
        return !isBusy && this.nextAvailableTime <= customer.getArrivalTime();
    }

    boolean isSameId(Server other) {
        return this.id == other.id;
    }

    Server serve(double endTime) {
        return new Server(this.id, endTime, true);
    }

    Server done(double eventTime) {
        return new Server(this.id, eventTime, false);
    }

    @Override
    public String toString() {
        return "server " + this.id;
    }
}
