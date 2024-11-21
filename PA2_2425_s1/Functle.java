import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Functle<T extends Movable<T>> {
    private final Function<T, T> normal;
    private final Function<T, T> reverse;

    private Functle() {
        this.normal = Function.identity();
        this.reverse = Function.identity();
    }

    private Functle(Function<T, T> normal, Function<T, T> reverse) {
        this.normal = normal;
        this.reverse = reverse;
    }

    static <T extends Movable<T>> Functle<T> of() {
        return new Functle<T>();
    }

    Functle<T> forward(int step) {
        Function<T, T> move = t -> {
            t.moveForward(step);
            return t;
        };
        Function<T, T> moveReverse = t -> {
            t.moveForward(-step);
            return t;
        };
        return new Functle<T>(this.normal.andThen(move),
            this.reverse.compose(moveReverse));
    }

    Functle<T> backward(int step) {
        return forward(-step);
    }

    Functle<T> left(int theta) {
        Function<T, T> turn = t -> {
            t.turnLeft(theta);
            return t;
        };
        Function<T, T> turnReverse = t -> {
            t.turnLeft(-theta);
            return t;
        };
        return new Functle<T>(this.normal.andThen(turn),
            this.reverse.compose(turnReverse));
    }

    Functle<T> right(int theta) {
        return left(-theta);
    }

    void run(T t) {
        this.normal.apply(t);
    }

    Functle<T> reverse() {
        if (this.reverse.equals(Function.identity())) {
            return this;
        }
        Function<T, T> reversedFunction = this.normal.andThen(this.reverse);
        return new Functle<T>(reversedFunction, Function.identity());
    }

    Functle<T> andThen(Functle<T> other) {
        return new Functle<T>(this.normal.andThen(other.normal),
            this.reverse.compose(other.reverse));
    }

    Functle<T> loop(int n) {
        return Stream.generate(() -> this)
            .limit(n)
            .reduce(new Functle<T>(),
                (x, y) -> x.andThen(y));
    }

    Functle<T> comeHome() {
        Function<T, T> function = t -> {
            Stream.iterate(Pair.of(this.normal, this.reverse),
                pair -> !t.equals(() -> pair.t().apply(t)),
                pair -> Pair.of(
                    pair.t().andThen(this.normal),
                    pair.u().compose(this.reverse)))
                .forEach(pair -> pair.u().apply(t));
            return t;
        };
        
        return new Functle<T>(function, Function.identity());
    }

    @Override
    public String toString() {
        return "Functle";
    }
}
