import java.util.Optional;

public class State {
    private final Shop shop;
    private final Customer customer;

    State(Shop shop) {
        this.shop = shop;
        this.customer = null;
    }

    State(Shop shop, Customer customer) {
        this.shop = shop;
        this.customer = customer;
    }

    public State next(State nextState) {
        if (this.customer == null) {
            return nextState;
        }

        return this.shop.findServer(this.customer)
            .map(server -> {
                Server updatedServer = server.serve(this.customer, 1.0);
                Shop updatedShop = this.shop.update(updatedServer);
                System.out.println(this.customer + " served by " + server);
                return new State(updatedShop, nextState.customer);
            })
            .orElseGet(() -> {
                System.out.println(this.customer + "leaves");
                return nextState;
            });
    }

    @Override
    public String toString() {
        return this.customer == null ? "" : this.customer + " arrives";
    }
}
