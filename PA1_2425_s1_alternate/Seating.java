import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Seating {
    private final List<Seat> seats;

    Seating(int capacity) {
        this.seats = Stream.<Seat>generate(() -> new Available())
        .limit(capacity).toList();
    }

    Seating(List<Seat> seatList) {
        this.seats = seatList;
    }

    boolean isAvailable(Pair<Integer, Integer> rowOfSeats) {

        int startIdx = rowOfSeats.t();
        int endIdx = startIdx + rowOfSeats.u();
        
        // we only check the seats if the specified row is valid
        if (startIdx >= 0 && endIdx <= seats.size()) {
            return IntStream.range(startIdx, endIdx)
                    .mapToObj(x -> seats.get(x))
                    .allMatch(x -> !x.isBooked());
        }
        
        return false;
    }

    Seating book(Pair<Integer, Integer> rowOfSeats) {

        if (this.isAvailable(rowOfSeats)) {
            int startIdx = rowOfSeats.t();
            int endIdx = startIdx + rowOfSeats.u();

            return new Seating(seats.stream()
            .map(x -> seats.indexOf(x) >= startIdx && seats.indexOf(x) < endIdx ?
            new Booked() : x)
            .toList());
        }
        
        return this;

    }

    @Override
    public String toString() {
        return seats.stream().map(x -> x.toString()).reduce("", (x,y) -> x + y);
    }
}
