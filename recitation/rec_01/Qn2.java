import java.util.stream.IntStream;
import java.util.stream.Stream;

public class qn2 {
    private static class Pair<T, U> {
        T first;
        U second;
        Pair(T t, U u) {
            first = t;
            second = u;
        }
        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }


    private static IntStream dot(int i, int j) {
        return IntStream.rangeClosed(i, j)
            .flatMap(x -> IntStream.rangeClosed(i, j)
                    .map(y -> x * y));
    }

    private static Stream<Pair<Integer, Integer>> product(int i, int j) {
        return IntStream.rangeClosed(i, j)
            .boxed()
            .flatMap(x -> IntStream.rangeClosed(i, j)
                    .mapToObj(y -> new Pair<Integer, Integer>(x, y)));
    }

    public static void main (String args[]) {
        //dot(1,3).forEach(x -> System.out.print(x + " "));
        product(1,3).forEach(x -> System.out.print(x + " "));
    }
}
