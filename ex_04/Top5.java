import java.util.List;
import java.util.stream.IntStream;

class Top5 {
    private final List<String> list;
    private static final int top = 5;

    Top5() {
        this.list = List.<String>of("","","","","");
    }

    Top5(List<String> list) {
        this.list = list;
    }

    Try<Top5> add(int i, String str) {
        if (i < top && i >= 0) {
            List<String> updated = IntStream.range(0,top)
                .mapToObj(x -> x == i ? str : this.list.get(x))
                .toList();
            return Try.<Top5>of(() -> new Top5(updated));
        }
        return Try.<Top5>of(() -> {
            throw new IllegalStateException(String.format("index %s out of range", i));
        });
    }

    Try<String> get(int i) {
        if (i < top && i >= 0) {
            return Try.of(() -> this.list.get(i));
        } else {
            return Try.of(() -> {
                throw new IllegalStateException(String.format("index %s out of range", i));
            });
        }
    }

    Try<Integer> find(String s) {
        if (this.list.indexOf(s) != -1) {
            return Try.of(() -> this.list.indexOf(s));
        }
        return Try.of(() -> {
            throw new IllegalStateException(String.format("%s not among top 5", s));
        });
    }

    public String toString() {
        return this.list.toString();
    }
}

