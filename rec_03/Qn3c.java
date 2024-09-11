IntPredicate and(IntPredicate p1, IntPredicate p2) {
    return value -> p1.test(value) && p2.test(value);
}
