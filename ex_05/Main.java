import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

class Main {

    static CompletableFuture<BusRoutes> processQuery(String query) {
        Scanner sc = new Scanner(query);
        BusStop srcBusStop = new BusStop(sc.next());
        String searchString = sc.next();
        sc.close();
        return BusSg.findBusServicesBetween(srcBusStop, searchString);
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n").tokens()
            .map(s -> processQuery(s))
            .toList() // create stream terminal, so that it processes the queries first
            .stream() // then, make a new stream
            .forEach(routes -> routes.thenCompose(r -> r.description())
                    .thenAccept(x -> System.out.println(x))
                    .join());
        sc.close();
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
