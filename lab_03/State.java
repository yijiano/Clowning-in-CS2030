import java.util.Optional;

public class State {
    private final PQ<Event> pq;
    private final Shop shop;
    private final String outputString;

    State(Shop shop) {
        this.pq = new PQ<Event>();
        this.shop = shop;
        this.outputString = "";
    }

    State(PQ<Event> pq, Shop shop) {
        this.pq = pq;
        this.shop = shop;
        this.outputString = "";
    }
    
    private State(PQ<Event> pq, Event event, Shop shop, String outputString) {
        this.pq = (event.hasNext())
            ? pq.add(event.next(shop).t())
            : pq;
        this.shop = (event.hasNext())
            ? event.next(shop).u()
            : shop;
        this.outputString = outputString + event;
    }
    
    public boolean isEmpty() {
        return pq.isEmpty() && this.outputString.equals("");
    }

    public State next() {
        Pair<Optional<Event>, PQ<Event>> polled = this.pq.poll();
        return polled.t()
           .map(x -> new State(polled.u(), x, this.shop, 
               (this.outputString == "" ? "" : this.outputString + "\n")))
           .orElse(new State(this.shop));
    }

    @Override
    public String toString() {
        return outputString;
    }
}
