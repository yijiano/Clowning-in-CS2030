import java.util.List;
import java.util.stream.Stream;

class Simulator {
    private final int numOfServers;
    private final int numOfCustomers;
    private final List<Pair<Integer, Pair<Double, Double>>> arrivals;

    Simulator(int numOfServers, int numOfCustomers,
            List<Pair<Integer,Pair<Double, Double>>> arrivals) {
        this.numOfServers = numOfServers;
        this.numOfCustomers = numOfCustomers;
        this.arrivals = arrivals;
    }

    State run() {
        Shop shop = new Shop(numOfServers);

        PQ<Event> pq = new PQ<Event>(arrivals.stream()
                .map(x -> new ArriveEvent(new Customer(x.t(), x.u().t(), x.u().u()), x.u().t()))
                    .toList());

        return Stream.iterate(new State(pq, shop), 
                    state -> !state.isEmpty(), 
                    state -> state.next())
            .reduce((first, second) -> second)
            .orElse(new State(shop));
    }
}
