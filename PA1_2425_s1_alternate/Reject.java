public class Reject extends Transaction {
    
    Reject(Seating plan, String log) {
        super(plan, log);
    }

    @Override
    public String toString() {
        return "REJECTED:\n" + super.toString();
    }
}
