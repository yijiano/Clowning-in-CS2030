public class Request extends Transaction {
    private final Pair<Integer, Integer> rowOfSeats;
    private final int billing;
    private final Bank bank;

    Request(Seating plan,
     Pair<Integer,Integer> rowOfSeats,
     int billing,
     Bank bank) {
        super(plan, "Requesting");
        this.rowOfSeats = rowOfSeats;
        this.billing = billing;
        this.bank = bank;
     }

    public Transaction transact(Transaction t) {
        if (t.canBook(rowOfSeats)) {
            Seating updatedPlan = t.bookSeats(rowOfSeats, this.isBillable());
            if (this.isBillable()) {
                Pair<Integer, Integer> bookedSeats = new Pair<Integer,Integer>(rowOfSeats.t(),
                                                    rowOfSeats.t() + rowOfSeats.u() - 1);
                return new Approve(updatedPlan,
                t.updateLog("billed " + this.billing + "; booked " + bookedSeats));
            }
            return new Reject(updatedPlan, t.updateLog("not billed " + this.billing));
        }

        return t;
    }

    private boolean isBillable() {
        return this.bank.apply(this.billing);
    }

    @Override
    public String toString() {
        return "REQUEST:\n" + super.toString();
    }
    
}
