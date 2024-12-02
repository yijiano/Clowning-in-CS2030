import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Functle<T extends Movable<T>> {
    private final Function<T, T> movementBuffer;
    private final Function<T, T> reverseBuffer;

    private Functle() {
        this.movementBuffer = Function.identity();
        this.reverseBuffer = Function.identity();
    }

    private Functle(Function<T, T> movementBuffer, Function<T, T> reverseBuffer) {
        this.movementBuffer = movementBuffer;
        this.reverseBuffer = reverseBuffer;
    }

    static <T extends Movable<T>> Functle<T> of() {
        return new Functle<T>();
    }

    Functle<T> forward(int steps) {
        Function<T, T> move = t -> {
            t.moveForward(steps);
            return t;
        };
        Function<T, T> moveReverse = t -> {
            t.moveForward(-steps);
            return t;
        };
        return new Functle<T>(this.movementBuffer.andThen(move),
            this.reverseBuffer.compose(moveReverse));
    }

    Functle<T> backward(int steps) {
        return forward(-steps);
    }

    Functle<T> left(int numRightAngles) {
        Function<T, T> turn = t -> {
            t.turnLeft(numRightAngles);
            return t;
        };
        Function<T, T> turnReverse = t -> {
            t.turnLeft(-numRightAngles);
            return t;
        };
        return new Functle<T>(this.movementBuffer.andThen(turn),
            this.reverseBuffer.compose(turnReverse));
    }

    Functle<T> right(int numRightAngles) {
        return left(-numRightAngles);
    }

    void run(T inputT) {
        this.movementBuffer.apply(inputT);
    }

    Functle<T> reverse() {
        if (this.reverseBuffer.equals(Function.identity())) {
            return this;
        }
        Function<T, T> reverseBufferdFunction = this.movementBuffer.andThen(this.reverseBuffer);
        return new Functle<T>(reverseBufferdFunction, Function.identity());
    }

    Functle<T> andThen(Functle<T> other) {
        return new Functle<T>(this.movementBuffer.andThen(other.movementBuffer),
            this.reverseBuffer.compose(other.reverseBuffer));
    }

    Functle<T> loop(int n) {
        return Stream.generate(() -> this)
            .limit(n)
            .reduce(new Functle<T>(),
                (x, y) -> x.andThen(y));
    }

    Functle<T> comeHome() {
        Function<T, T> function = t -> {
            Stream.iterate(Pair.of(this.movementBuffer, this.reverseBuffer),
                pair -> !t.equals(() -> pair.t().apply(t)),
                pair -> Pair.of(
                    pair.t().andThen(this.movementBuffer),
                    pair.u().compose(this.reverseBuffer)))
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
