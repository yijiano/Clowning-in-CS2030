import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Shop {
    private final List<Server> server_list;

    Shop(int server_count) {
        this.server_list = IntStream
            .rangeClosed(1, server_count)
            .mapToObj(i -> new Server(i))
            .toList();
    }

    Shop(List<Server> new_server_list) {
        this.server_list = new_server_list;
    }

    public Optional<Server> findServer(Customer c) {
        return server_list.stream()
            .filter(x -> x.canServe(c))
            .findFirst();
    }

    public Shop update(Server s) {
        List<Server> new_server_list = this.server_list.stream()
            .map(server -> server.getId() == s.getId() ? s : server)
            .toList();
        return new Shop(new_server_list);
    }

    @Override
    public String toString() {
        return this.server_list.toString();
    }
}
