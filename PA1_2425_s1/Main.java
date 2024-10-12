import java.util.stream.Stream;

Transaction process(Stream<Transaction> transactions, Init init) {
    return transactions.reduce(init, (x, y) -> y.transact(x));
}

void main() {}

