import java.util.List;
import java.util.stream.Stream;

List<Booking> findBestBooking(Request request, List<Driver> list) {
    return list.stream()
        .map(x -> new Booking(x, request))
        .flatMap(x -> x.listAllServices()
            .stream())
        .sorted()
        .toList();
}

void main() {}
