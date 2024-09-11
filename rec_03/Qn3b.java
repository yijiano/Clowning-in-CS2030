IntPredicate and(IntPredicate p1, IntPredicate p2) {
    return new IntPredicate() {
        @Override
        public boolean test(int value) {
            return p1.test(value) && p2.test(value);
        }
    };
}
