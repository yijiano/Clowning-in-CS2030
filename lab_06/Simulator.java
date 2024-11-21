import java.util.List;
import java.util.stream.Stream;
import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int numOfCustomers;
    private final List<Pair<Integer, Double>> arrivals;
    private final int queueMaxSize;
    private final Supplier<Double> inputServiceTimeSupplier;

    Simulator(int numOfServers, int queueMaxSize, Supplier<Double> inputServiceTimeSupplier,
            int numOfCustomers, List<Pair<Integer, Double>> arrivals) {
        this.numOfServers = numOfServers;
        this.queueMaxSize = queueMaxSize;
        this.inputServiceTimeSupplier = inputServiceTimeSupplier;
        this.numOfCustomers = numOfCustomers;
        this.arrivals = arrivals;
    }

    State run() {
        Shop shop = new Shop(
                this.numOfServers,
                this.inputServiceTimeSupplier,
                this.queueMaxSize);

        PQ<Event> pq = new PQ<Event>(arrivals
                .stream()
                .map(x -> new ArriveEvent(
                        new Customer(x.t(), x.u()), x.u()))
                .toList());

        return Stream.iterate(new State(pq, shop),
                state -> !state.isEmpty(),
                state -> state.next())
            .reduce((first, second) -> second)
            .orElse(new State(shop))
            .outputWithStatistics();
    }
}
