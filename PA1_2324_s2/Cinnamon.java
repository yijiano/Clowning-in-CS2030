class Cinnamon implements Drink {
    private final Drink drink;    
    private static final String nut1 = "fat";
    private static final int ml1 = 220;
    private static final String nut2 = "sugar";
    private static final int ml2 = 150;
    
    Cinnamon(Drink drink) {
        this.drink = drink;
    }

    public Nutri value() {
        return drink.value().update(nut1, ml1).update(nut2, ml2);
    }

    public String prep(String initValue) {
        return this.drink.prep() + initValue + "<" + this.toString();
    }

    public String prep() {
        return this.prep("");
    }

    public String toString() {
        return "cinnamon";
    }
}

