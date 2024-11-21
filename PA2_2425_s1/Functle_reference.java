import java.util.function.Function;
import java.util.stream.Stream;

class Functle<T extends Movable<T>> {
    private final Function<T, T> normal;
    private final Function<T, T> reverse;

    private Functle() {
        this.normal = Function.identity();
        this.reverse = Function.identity();
    }

    private Functle(Function<T, T> normal, Function<T ,T> reverse) {
        this.normal = normal;
        this.reverse = reverse; 
    }

    private Functle(Functle<T> functle, Function<T, T> normal, Function<T ,T> reverse) {
        this.normal = functle.normal.andThen(normal);
        this.reverse = functle.reverse.compose(reverse); 
    }

    static <T extends Movable<T>> Functle<T> of() {
        return new Functle<T>();
    }

    private T forward(T t, int step) {
        t.moveForward(step);
        return t;
    }

    private T left(T t, int theta) {
        t.turnLeft(theta);
        return t;
    }

    Functle<T> forward(int step) {
        return new Functle<T>(this, t -> forward(t, step), t -> forward(t, -step));
    }

    Functle<T> left(int theta) {
        return new Functle<T>(this, t -> left(t, theta), t -> left(t, -theta));
    }

    Functle<T> backward(int step) {
        return new Functle<T>(this, t -> forward(t, -step), t -> forward(t, step));
    }

    Functle<T> right(int theta) {
        return new Functle<T>(this, t -> left(t, -theta), t -> left(t, theta));
    }

    void run(T t) {
        this.normal.apply(t);
    }

    Functle<T> reverse() {
        if (this.reverse.equals(Function.<T>identity())) {
            return this;
        }
        return new Functle<T>(this.normal.andThen(this.reverse),
            Function.<T>identity());
    }

    Functle<T> andThen(Functle<T> other) {
        return new Functle<T>(this.normal.andThen(other.normal),
            this.reverse.compose(other.reverse));
    }

    Functle<T> loop(int n) {
        return Stream.generate(() -> this)
            .limit(n)
            .reduce(new Functle<T>(), (x, y) -> x.andThen(y));
    }

    Functle<T> comeHome() {
        Pair<Function<T, T>, Function<T, T>> startingPair = 
            Pair.<Function<T, T>, Function<T, T>>of(this.normal, this.reverse);

        Function<T, T> function = t -> {
            Stream.iterate(startingPair, 
                    pair -> !t.equals(() -> pair.t().apply(t)),
                    pair -> Pair.<Function<T, T>, Function<T, T>>of(
                        pair.t().andThen(this.normal),
                        pair.u().compose(this.reverse)))
                .forEach(pair -> pair.u().apply(t));

            return t;

        };

        return new Functle(function, Function.<T>identity());
    }

    @Override
    public String toString() {
        return "Functle";
    }
}
