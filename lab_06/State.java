import java.util.Optional;

class State {
    private final PQ<Event> pq;
    private final Shop shop;
    private final String outputString;

    // Statistics
    private final double totalWaitingTime;
    private final int numCustomersServed;
    private final int numCustomersLeft;

    State(PQ<Event> pq, Shop shop) {
        this.pq = pq;
        this.shop = shop;
        this.outputString = "";
        this.totalWaitingTime = 0.0;
        this.numCustomersServed = 0;
        this.numCustomersLeft = 0;
    }

    State(Shop shop) {
        this.pq = new PQ<>();
        this.shop = shop;
        this.outputString = "";
        this.totalWaitingTime = 0.0;
        this.numCustomersServed = 0;
        this.numCustomersLeft = 0;
    }

    // Private Constructor to Parse Events
    private State(PQ<Event> pq, Event event, Shop shop, String outputString,
            double totalWaitingTime, int numCustomersServed, int numCustomersLeft) {

        Pair<Event, Shop> nextEvent = event.next(shop);
        this.pq = event.hasNext() ? pq.add(nextEvent.t()) : pq;
        this.shop = nextEvent.u();
        this.outputString = outputString + (event.toString().isEmpty()
                ? ""
                : (outputString.isEmpty() ? "" : "\n") + event.toString());

        this.totalWaitingTime = totalWaitingTime + event.addWaitingTime();
        this.numCustomersServed = numCustomersServed + event.addNumCustomersServed();
        this.numCustomersLeft = numCustomersLeft + event.addNumCustomersLeft();
    }

    // Private Constructor For Statistics
    private State(PQ<Event> pq, Shop shop, String outputString,
            double totalWaitingTime, int numCustomersServed, int numCustomersLeft) {

        this.pq = pq;
        this.shop = shop;
        this.outputString = outputString;
        this.totalWaitingTime = totalWaitingTime;
        this.numCustomersServed = numCustomersServed;
        this.numCustomersLeft = numCustomersLeft;
    }

    boolean isEmpty() {
        return pq.isEmpty() && this.outputString.equals("");
    }

    State next() {
        return this.pq.poll().t()
            .map(event -> new State(
                this.pq.poll().u(), 
                event, 
                this.shop, 
                this.outputString,
                this.totalWaitingTime, 
                this.numCustomersServed, 
                this.numCustomersLeft))
            .orElse(new State(this.shop));
    }

    private String getStatistics() {
        double avgWaitingTime = this.totalWaitingTime == 0
            ? 0.0
            : this.totalWaitingTime / numCustomersServed;
        
        return String.format("[%.3f %d %d]",
            avgWaitingTime, numCustomersServed, numCustomersLeft);
    }

    State outputWithStatistics() {
        return new State(this.pq, this.shop,
                this.outputString + "\n" + this. getStatistics(),
                this.totalWaitingTime, this.numCustomersServed, this.numCustomersLeft);
    }

    @Override
    public String toString() {
        return outputString;
    }
}
