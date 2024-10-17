import java.util.function.Supplier;
import java.util.function.Function;

interface Try {
    static<T> Try<T> of(Supplier<? extends T> supplier) {
        try {
            return succ(supplier.get());
        } catch (Exception ex) {
            return fail(ex);
        }
    }

    private static <T> Try<T> succ(T t) {
        return new Try<T>() {
            public T orElse(T other) {
                return t;
            }

            public T orElseGet(Supplier<? extends T> s) {
                return t;
            }

            public <R> Try<R> map (Function<? super T, ? extends R> mapper) {
                R r = mapper.apply(t);
                return Try.<R>of(() -> r);
            }

            public <R> Try<R> flatMap (Function<? super T, ? extends Try<? extends R>> mapper) {
                return mapper.apply(t).map(Function.<R>identity());
            }

            public String toString() {
                return "Succcess: " + t;
            }
        };
    }

    private static <T> Try<T> fail(Exception ex) {
        return new Try<T>() {
            public T orElse(T other) {
                return other;
            }

            public T orElseGet(Supplier<? extends T> s) {
                return s.get();
            }

            public <R> Try<R> map (Function<? super T, ? extends R> m) {
                return Try.<R>fail(ex);
            }

            public <R> Try<R> flatMap (Function<? super T, ? extends Try<? extends R>> m) {
                return Try.<R>fail(ex);
            }

            public String toString() {
                return "Failure: " + ex;
            }
        };
    }

    <R> Try<R> map (Function<? super T, ? extends R> m);
    <R> Try<R> flatMap (Function<? super T, ? extends R> m);
}
