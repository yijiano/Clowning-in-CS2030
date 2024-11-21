import java.util.function.Supplier;
import java.util.function.Function;

interface Try<T> {
    static <T> Try<T> of(Supplier<? extends T> supplier) {
        try {
            return succ(supplier.get());
        } catch (Exception ex) {
            return fail(ex);
        }
    }

    private static <T> Try<T> succ(T t) {
        return new Try<T>() {
            public T get() {
                return t;
            }

            public T orElse(T other) {
                return t;
            }

            public T orElseGet(Supplier<? extends T> s) {
                return t;
            }
            
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Try<?> other) {
                    return this.get().equals(other.get());
                }
                return false;
            }

            public <R> Try<R> map(Function<? super T, ? extends R> mapper) {
                return Try.<R>of(() -> mapper.apply(t));
            }

            public <R> Try<R> flatMap(Function<? super T, ? extends Try<? extends R>> mapper) {
                return mapper.apply(t).map(Function.<R>identity());
            }

            public String toString() {
                return "Success: " + t;
            }
        };
    }

    private static <T> Try<T> fail(Exception ex) {
        return new Try<T>() {
            public Exception get() {
                return ex;
            }

            public T orElse(T other) {
                return other;
            }

            public T orElseGet(Supplier<? extends T> s) {
                return s.get();
            }

            public boolean equals(Object obj) {
                return this.toString().equals(obj.toString());
            }

            public <R> Try<R> map(Function<? super T, ? extends R> m) {
                return Try.<R>fail(ex);
            }

            public <R> Try<R> flatMap(Function<? super T, ? extends Try<? extends R>> m) {
                return Try.<R>fail(ex);
            }

            public String toString() {
                return "Failure: " + ex;
            }
        };
    }

    <R> Try<R> map(Function<? super T, ? extends R> mapper);

    <R> Try<R> flatMap(Function<? super T, ? extends Try<? extends R>> mapper);

    Object get();

    T orElse(T other);

    T orElseGet(Supplier<? extends T> supplier);

    boolean equals(Object obj);
}
