Log<Integer> sum(int n) {
    return n == 0 
        ? Log.<Integer>of(0, "hit base case!")
        : sum(n-1).flatMap(x -> Log.<Integer>of(x + n, "adding " + n));
}

Log<Integer> f(int n) {
    if (n == 1) {
        return Log.<Integer>of(1, "1");
    }
    
    if (n % 2 == 0) { 
        return Log.<Integer>of(n / 2, n + " / 2")
            .flatMap(x -> f(n / 2)); 
    }
    
    return Log.<Integer>of(3 * n + 1, String.format("3(%d) + 1", n))
        .flatMap(x -> f(3 * n + 1)); 
}
