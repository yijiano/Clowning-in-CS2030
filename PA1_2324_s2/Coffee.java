class Coffee implements Drink {
    private static final String nut = "caffeine";
    private static final int ml = 100;

    Coffee() {
    }

    public Nutri value() {
        return new Nutri(nut, ml);
    }

    public String prep() {
        return "[" + this.toString() + "]";
    }

    public String prep(String initValue) {
        int firstTopping = initValue.indexOf("<");
        int lastIng = initValue.indexOf(">");

        if (firstTopping != -1) {
            initValue = initValue.replace("<", this.prep() + "<");
        } else if (lastIng != -1) {
            initValue = initValue.replace(">", ">" + this.prep());
        } else {
            initValue = this.prep();
        }
        return initValue;
    }

    public String toString() {
        return "coffee";
    }
}
