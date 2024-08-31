import java.util.stream.Stream;

public class Qn3 {

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

    private static Stream<Integer> fibonacci (int n) {
        return Stream.iterate(new Pair<Integer, Integer>(1, 1), 
                p -> new Pair<Integer, Integer>(p.second, p.first + p.second))
            .map(p -> p.first)
            .limit(n);
    }

    public static void main(String args[]) {
        fibonacci(10).forEach(num -> System.out.print(num + " "));
    }
}
