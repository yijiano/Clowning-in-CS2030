import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Stream;
import java.util.concurrent.CompletableFuture;

class BusSg {
    public static CompletableFuture<List<BusService>> getBusServices(BusStop stop) {
        return BusAPI.getBusServicesAt(stop.getStopId())
            .thenApply(str -> {
                Scanner sc = new Scanner(str);
                List<BusService> busServices = sc
                    .useDelimiter("\n")
                    .tokens()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .map(id -> new BusService(id))
                    .sorted()
                    .toList();
                sc.close();
                return busServices;
            });
    }

    public static CompletableFuture<BusRoutes> findBusServicesBetween(BusStop stop,
            String searchString) {
        return getBusServices(stop)
            .thenApply(services -> services.stream()
                    .collect(() -> 
                        new LinkedHashMap<BusService,CompletableFuture<List<BusStop>>>(),
                        (map, service) -> map.put(service, service.findStopsWith(searchString)),
                        (map1,map2) -> map1.putAll(map2)))
            .thenApply(validServices -> new BusRoutes(stop, searchString, validServices))
            .handle((res, e) -> {
                if (res == null) {
                    System.err.println("Unable to complete query: " + e);
                    return new BusRoutes(stop, searchString, Map.of());
                } else {
                    return res;
                }
            });
    }
}
