public class Server {
    private final int id;
    private final double nextAvailableTime;

    Server(int id) {
        this.id = id;
        this.nextAvailableTime = 0.0;
    }

    Server(int id, double nextAvailableTime) {
        this.id = id;
        this.nextAvailableTime = nextAvailableTime;
    }

    public Server serve(Customer c, double serviceTime) {
        return new Server(this.id, c.serveTill(serviceTime));
    }

    public boolean canServe(Customer c) {
        return c.canBeServed(this.nextAvailableTime);
    }

    public boolean isSameId(Server otherServer) {
        return this.id == otherServer.id;
    }

    @Override
    public String toString() {
        return ("server " + id);
    }
}
