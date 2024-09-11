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

    public int getId() {
        return this.id;
    }
    
    public Server serve(Customer c, double serviceTime) {
        return new Server(this.id, c.serveTill(serviceTime));
    }

    public boolean canServe(Customer c) {
        return c.canBeServed(this.nextAvailableTime);
    }

    @Override
    public String toString() {
        return ("server " + id);
    }
}
