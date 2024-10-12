class Sugar implements Drink {
    private final Drink drink;    
    private static final String nut = "sugar";
    private static final int ml = 8000;

    Sugar(Drink drink) {
        this.drink = drink;
    }

    public Nutri value() {
        return this.drink.value().update(nut, ml);
    }

    public String prep(String initValue) {
        return this.toString() + ">" + initValue + this.drink.prep();
    }

    public String prep() {
        return this.prep("");
    }

    public String toString() {
        return "sugar";
    }
}


