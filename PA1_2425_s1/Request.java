public class Request extends Transaction {
    private final Pair<Integer, Integer> rowOfSeats;
    private final int billing;
    private final Bank bank;

    Request(Seating plan, Pair<Integer,Integer> rowOfSeats, int billing, Bank bank) {
        super(plan, "");
        this.rowOfSeats = rowOfSeats;
        this.billing = billing;
        this.bank = bank;
    }

    @Override
    public Transaction transact(Transaction t) {
        if (!t.getPlan().isAvailable(this.rowOfSeats)) {
            return t; 
        }

        boolean validBank = this.bank.test(this.billing);

        String newLog = t.getLog() + "\n" + (validBank
            ? "billed " + this.billing + "; booked " + rowOfSeats.t() + "--" 
                + (rowOfSeats.t() + rowOfSeats.u() - 1) 
            : "not billed " + this.billing);

        Seating newPlan = validBank ? t.getPlan().book(this.rowOfSeats) : t.getPlan();

        return validBank
            ? new Approve(newPlan, newLog)
            : new Reject(newPlan, newLog);
    }

    @Override
    public String toString() {
        return "REQUEST:" + "\n"
            + "Requesting" + "\n"
            + this.getPlan();
    }
}
