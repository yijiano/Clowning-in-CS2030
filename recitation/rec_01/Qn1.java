import java.util.stream.IntStream;

public class Qn1 {

    private static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n)
            .noneMatch(x -> n % x == 0);
    }

    private static int numPrimeFactors(int n) {
        return (int) IntStream.rangeClosed(1, n)
            .filter(x -> n % x == 0)
            .filter(x -> isPrime(x)).count();
    }

    private static IntStream omega(int n) {
        return IntStream.rangeClosed(1, n)
            .map(x -> numPrimeFactors(x));
    }

    public static void main(String args[]) {
        omega(10).forEach(x -> System.out.print(x + " "));
    }
}
