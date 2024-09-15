import java.util.Optional;

public class State {
    private final Shop shop;
    private final Optional<Customer> customer;
    private final String outputString;

    State(Shop shop) {
        this.shop = shop;
        this.customer = Optional.empty(); 
        this.outputString = "";
    }

    State(Shop shop, Customer customer) {
        this.shop = shop;
        this.customer = Optional.of(customer);
        this.outputString = customer + " arrives" + "\n";
    }

    private State(Shop shop, Customer customer, String outputString) {
        this.customer = Optional.of(customer);

        Optional<Server> newServer = shop.findServer(customer);

        this.shop = newServer.map(x -> shop.update(x.serve(customer, 1.0)))
            .orElse(shop);

        this.outputString = outputString + customer
            + newServer.map(x -> new String(" served by " + x))
                .orElse(new String(" leaves"))
            + "\n";
    }

    public State next(State newState) {
        return newState.customer
            .map(x -> new State(this.shop, x, this.outputString + newState.outputString))
            .orElse(this);
    }

    @Override
    public String toString() {
        return outputString;
    }
}
