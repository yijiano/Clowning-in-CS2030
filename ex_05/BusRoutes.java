import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

class BusRoutes {
    final BusStop stop;
    final String name;
    final Map<BusService,CompletableFuture<List<BusStop>>> services;

    BusRoutes(BusStop stop, String name,
            Map<BusService,CompletableFuture<List<BusStop>>> services) {
        this.stop = stop;
        this.name = name;
        this.services = services;
    }

    public CompletableFuture<String> description() {
        String result = "Search for: " + this.stop + " <-> " + name + ":\n";
        result += "From " +  this.stop + "\n";

        CompletableFuture<String> cfresult = CompletableFuture.completedFuture(result);

        for (BusService service: services.keySet()) {
            cfresult = cfresult.thenCombine(services.get(service),
                     (res, stops) -> res + describeService(service, stops));
        }
        return cfresult;
    }

    public String describeService(BusService service, List<BusStop> stops) {
        if (stops.isEmpty()) {
            return "";
        } 
        return stops.stream()
            .filter(stop -> stop != this.stop) 
            .reduce("- Can take " + service + " to:\n",
                (str, stop) -> str += "  - " + stop + "\n",
                (str1, str2) -> str1 + str2);
    }
}
