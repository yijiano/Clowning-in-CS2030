public class Init extends Transaction {
    
    Init(Seating plan) {
        super(plan, "Initializing");
    }

    Transaction transact(Transaction t) {
        return this;
    }

    @Override
    public String toString() {
        return "INIT:\n" + super.toString();
    }

}
