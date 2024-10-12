import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <T> Maybe<T> of(T value) {
        return new Maybe<T>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private T get() {
        return this.value;
    }

    Maybe<T> filter(Predicate<? super T> pred) {
        if (this.isEmpty()) {
            return this;
        }
        if (pred.test(this.get())) {
            return this;
        }
        return Maybe.<T>empty();
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        }

        R r = mapper.apply(this.get());
        return Maybe.<R>of(r);
    }

    <R> Maybe<R> flatMap(Function<? super T, Optional<? extends R>> mapper) {
        return null;
    }

    T orElse(T other) {
        if (this.isEmpty()) {
            return other;
        }
        return this.get();
    }

    T orElseGet(Supplier<? extends T> supplier) {
        if (this.isEmpty()) {
            return supplier.get();
        }
        return this.get();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Maybe<?> other) {
            return this.get().equals(other.get());
        }
        return false;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "Maybe.empty";
        }
        return "Maybe[" + this.get() + "]";
    }
}
