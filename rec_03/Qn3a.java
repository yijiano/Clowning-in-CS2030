class AndPredicate implements IntPredicate {
    private final IntPredicate p1;
    private final IntPredicate p2;

    AndPredicate(IntPredicate p1, IntPredicate p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean test(int value) {
        return p1.test(value) && p2.test(value);
    }
}

IntPredicate and(IntPredicate p1, IntPredicate p2) {
    return new AndPredicate(p1, p2);
}
