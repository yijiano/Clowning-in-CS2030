import java.util.List;
import java.util.stream.IntStream;

public class Seating {
    private final List<Seat> seatsList;
    private final int capacity;

    public Seating(int capacity) {
        this.capacity = capacity;
        this.seatsList = IntStream
            .range(0, capacity)
            .<Seat>mapToObj(x -> new Available())
            .toList();
    }

    private Seating(List<Seat> seatsList) {
        this.seatsList = seatsList;
        this.capacity = seatsList.size();
    }

    public boolean isAvailable(Pair<Integer, Integer> rowOfSeats) {
        if (rowOfSeats.t() + rowOfSeats.u() - 1 >= this.capacity) {
            return false;
        }
        
        if (rowOfSeats.t() < 0 || rowOfSeats.u() < 0) {
            return false;
        }

        return IntStream.range(rowOfSeats.t(), rowOfSeats.t() + rowOfSeats.u())
            .filter(index -> this.seatsList.get(index).isBooked() == new Booked().isBooked())
            .count() == 0;
    }

    private boolean isSeatInRow(int seat, Pair<Integer, Integer> rowOfSeats) {
        return seat >= rowOfSeats.t() && seat <= rowOfSeats.t() + rowOfSeats.u() - 1;
    }

    public Seating book(Pair<Integer, Integer> rowOfSeats) {
        if (!this.isAvailable(rowOfSeats)) {
            return this;
        }

        List<Seat> copiedList = IntStream.range(0, this.capacity)
            .mapToObj(index -> isSeatInRow(index, rowOfSeats)
                    ? new Booked()
                    : this.seatsList.get(index))
            .toList();

        return new Seating(copiedList);
    }

    @Override
    public String toString() {
        return this.seatsList.stream()
            .map(x -> x.toString())
            .reduce("", (x,y) -> x + y);
    }

}
