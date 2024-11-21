class LeaveEvent extends Event {

    LeaveEvent(Customer customer, double eventTime) {
        super(eventTime, customer);
    }

    @Override
    int addNumCustomersLeft() {
        return 1;
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}
