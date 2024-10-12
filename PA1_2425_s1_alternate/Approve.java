public class Approve extends Transaction {
    
    Approve(Seating plan, String log) {
        super(plan, log);
    }

    @Override
    public String toString() {
        return "APPROVED:\n" + super.toString();
    }
}
