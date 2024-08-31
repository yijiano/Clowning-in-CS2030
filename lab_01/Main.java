import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

boolean isPrime(int n) {
    return n > 1 && IntStream
        .range(2, n)
        .noneMatch(x -> n % x == 0);
}

IntStream twinPrimes(int n) {
    return IntStream.rangeClosed(2, n)
        .filter(x -> isPrime(x) && (isPrime(x - 2) || isPrime(x + 2)));
}

String reverse(String str) {
    int n = str.length();
    return IntStream.range(0, n)
        .mapToObj(x -> new String(str.substring(n - 1 - x, n - x)))
        .reduce(new String(), (x, y) -> x + y);
}

int countRepeats(List<Integer> list) {
    int n = list.size();
    int first_pair = list.get(0).equals(list.get(1)) ? 1 : 0;
    
    return n <= 2 
        ? first_pair
        : first_pair + IntStream.range(1, n - 1)
            .filter(x -> list.get(x).equals(list.get(x + 1)) && !list.get(x).equals(list.get(x - 1)))
            .reduce(0, (x, y) -> x + 1);
}


void main(String args[]) {}
