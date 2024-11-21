import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.Supplier;
import java.util.Comparator;

class Shop {
    private final List<Pair<Server, Integer>> serverList;
    private final Supplier<Double> serviceTime;
    private final int queueMaxSize;

    Shop(int numOfServers, Supplier<Double> serviceTime, int queueMaxSize) {
        this.serverList = IntStream
            .rangeClosed(1, numOfServers)
            .mapToObj(i -> new Pair<>(new Server(i), 0))
            .toList();
        this.serviceTime = serviceTime;
        this.queueMaxSize = queueMaxSize;
    }

    private Shop(List<Pair<Server,Integer>> serverList, 
            Supplier<Double> serviceTime, int queueMaxSize) {
        this.serverList = serverList;
        this.serviceTime = serviceTime;
        this.queueMaxSize = queueMaxSize;
    }

    private int getQueueSize(Server server) {
        return this.serverList.stream()
            .filter(pair -> pair.t().isSameId(server))
            .findFirst()
            .map(pair -> pair.u())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Server"));
    }

    private boolean isQueueFull(Server server) {
        return getQueueSize(server) >= queueMaxSize;
    }

    double getServiceTime() {
        return this.serviceTime.get();
    }

    Server getUpdatedServer(Server server) {
        return this.serverList.stream()
            .filter(pair -> pair.t().isSameId(server))
            .findFirst()
            .map(pair -> pair.t())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Server"));
    }

    Optional<Server> findServer(Customer customer) {
        return this.serverList.stream()
            .filter(pair -> !pair.t().isBusy())
            .filter(pair -> pair.t().canServe(customer))
            .findFirst()
            .map(pair -> pair.t())
            .or(() -> this.serverList.stream()
                    .filter(pair -> !isQueueFull(pair.t()))
                    .findFirst()
                    .map(pair -> pair.t()));
    }

    private Shop update(Pair<Server, Integer> updatedServer) {
        List<Pair<Server, Integer>> newServerList = this.serverList
            .stream()
            .map(pair -> pair.t().isSameId(updatedServer.t()) 
                    ? updatedServer 
                    : pair)
            .toList();

        return new Shop(newServerList, this.serviceTime, this.queueMaxSize);
    }

    Shop updateServer(Server server) {
        return update(new Pair<>(server, getQueueSize(server)));
    }

    Shop pushServerQueue(Server server) {
        return update(new Pair<>(server, getQueueSize(server) + 1));
    }

    Shop popServerQueue(Server server) {
        return update(new Pair<>(server, getQueueSize(server) - 1));
    }

    @Override
    public String toString() {
        return this.serverList.toString();
    }
}
