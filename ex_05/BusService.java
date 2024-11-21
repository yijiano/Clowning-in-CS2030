import java.util.Scanner;
import java.util.List;
import java.util.concurrent.CompletableFuture;

class BusService implements Comparable<BusService> {
    private final String serviceId;

    public BusService(String id) {
        this.serviceId = id;
    }

    public CompletableFuture<List<BusStop>> getBusStops() {
        return BusAPI.getBusStopsServedBy(serviceId)
            .thenApply(str -> {
                Scanner sc = new Scanner(str);
                List<BusStop> stops = sc.useDelimiter("\n")
                    .tokens()
                    .map(line -> line.split(","))
                    .map(fields -> new BusStop(fields[0], fields[1]))
                    .toList();
                sc.close();
                return stops;
            });
    }

    public CompletableFuture<List<BusStop>> findStopsWith(String name) {
        return getBusStops()
            .thenApply(stops -> stops.stream()
                .filter(stop -> stop.matchName(name))
                .toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof BusService busService) {
            return this.serviceId.equals(busService.serviceId);
        } else {
            return false;
        }
    }

    public int compareTo(BusService other) {
        return this.serviceId.compareTo(other.serviceId);
    }

    @Override
    public int hashCode() {
        return serviceId.hashCode();
    }

    @Override
    public String toString() {
        return serviceId;
    }
}
