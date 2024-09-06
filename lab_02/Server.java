public class Server {
    private final int id;
    private final double next_available_time;

    Server(int id) {
        this.id = id;
        this.next_available_time = 0.0;
    }

    Server(int id, double next_available_time) {
        this.id = id;
        this.next_available_time = next_available_time;
    }

    public int getId() {
        return this.id;
    }
    
    public Server serve(Customer c, double service_time) {
        return new Server(this.id, c.serveTill(service_time));
    }

    public boolean canServe(Customer c) {
        return c.canBeServed(this.next_available_time);
    }

    @Override
    public String toString() {
        return ("server " + id);
    }
}
