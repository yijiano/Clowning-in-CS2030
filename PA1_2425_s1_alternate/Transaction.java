abstract class Transaction {
    private final Seating plan;
    private final String log;
    
    Transaction(Seating plan) {
        this.plan = plan;
        this.log = "";
    }

    Transaction(Seating plan, String log) {
        this.plan = plan;
        this.log = log;
    }

    Transaction transact(Transaction t) {
        return this;
    }

    public boolean canBook(Pair<Integer,Integer> rowOfSeats) {
        return this.plan.isAvailable(rowOfSeats);
    }

    public Seating bookSeats(Pair<Integer,Integer> rowOfSeats, boolean isBillable) {
        if (isBillable) {
            return this.plan.book(rowOfSeats);
        }
        return this.plan;
    }

    public String updateLog(String newLog) {
        return this.log + "\n" + newLog;
    }

    public Seating getPlan() {
        return this.plan;
    }

    @Override
    public String toString() {
        return this.log + "\n" + this.plan.toString();
    }
}