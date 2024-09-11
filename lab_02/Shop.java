import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Shop {
    private final List<Server> serverList;

    Shop(int numOfServers) {
        this.serverList = IntStream
            .rangeClosed(1, numOfServers)
            .mapToObj(i -> new Server(i))
            .toList();
    }

    Shop(List<Server> newServerList) {
        this.serverList = newServerList;
    }

    public Optional<Server> findServer(Customer c) {
        return serverList.stream()
            .filter(x -> x.canServe(c))
            .findFirst();
    }

    public Shop update(Server newServer) {
        List<Server> newServerList = this.serverList.stream()
            .map(s -> s.getId() == newServer.getId() ? newServer : s)
            .toList();
        return new Shop(newServerList);
    }

    @Override
    public String toString() {
        return this.serverList.toString();
    }
}
