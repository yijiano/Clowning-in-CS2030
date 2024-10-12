class Milk implements Drink {
    private final Drink drink;    
    private static final String nut1 = "fat";
    private static final int ml1 = 2680;
    private static final String nut2 = "sugar";
    private static final int ml2 = 4340;
    
    Milk(Drink drink) {
        this.drink = drink;
    }

    public Nutri value() {
        return this.drink.value().update(nut1, ml1).update(nut2, ml2);
    }

    public String prep(String initValue) {
        return this.toString() + ">" + initValue + this.drink.prep();
    }

    public String prep() {
        return this.prep("");
    }

    public String toString() {
        return "milk";
    }
}

