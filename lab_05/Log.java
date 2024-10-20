import java.util.function.Supplier;
import java.util.function.Function;
import java.util.Optional;

public class Log<T> {
    private final T value;
    private final String log;

    private Log(T value, String log) {
        this.value = value;
        this.log = log;
    }

    static <T> Log<T> of(T value, String log) {
        return Optional.ofNullable(value)
            .filter(x -> !(x instanceof Log))
            .flatMap(x -> Optional.ofNullable(log))
            .map(x -> new Log<T>(value, log))
            .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    static <T> Log<T> of(T value) {
        return Log.of(value, "");
    }

    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return Log.<R>of(mapper.apply(this.value), this.log);
    }

    <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
        Log<? extends R> mapped = mapper.apply(this.value);

        return Log.<R>of(mapped.value, this.log 
            + (mapped.log.isEmpty() 
                ? "" 
                : (this.log.isEmpty() ? "" : "\n") + mapped.log));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj instanceof Log<?> other) {
            return this.value.equals(other.value) && this.log.equals(other.log);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Log[" + this.value.toString() + "]"
            + (this.log.isEmpty() ? "" : "\n" + this.log);
    }
}
