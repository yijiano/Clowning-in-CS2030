import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletableFuture;

class BusAPI {
    private static final int HTTP_RESPONSE_STATUS_OK = 200;

    private static final String BUS_SERVICE_API = 
        "https://raw.githubusercontent.com/nus-cs2030/2324-s2/main/bus_services/";

    private static final String BUS_STOP_API = 
        "https://raw.githubusercontent.com/nus-cs2030/2324-s2/main/bus_stops/";

    private static CompletableFuture<String> httpGet(String url) {
        HttpClient client = HttpClient.newBuilder()
            .build();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        return client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(r -> {
                if (r.statusCode() != HTTP_RESPONSE_STATUS_OK) {
                    System.out.println(r + " " + r.statusCode());
                    return "";
                }
                return r.body();
            });
    }

    public static CompletableFuture<String> getBusStopsServedBy(String serviceId) {
        return httpGet(BUS_SERVICE_API + serviceId);
    }

    public static CompletableFuture<String> getBusServicesAt(String stopId) {
        return httpGet(BUS_STOP_API + stopId);
    }
}
