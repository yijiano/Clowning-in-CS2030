public abstract class Transaction {
    private final Seating plan;
    private final String log;

    Transaction (Seating plan, String log) {
        this.plan = plan;
        this.log = log;
    }

    public Seating getPlan() {
        return this.plan;
    }

    public String getLog() {
        return this.log;
    }

    public Transaction transact(Transaction t) {
        return this;
    }
}
