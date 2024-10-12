class Audit {
    private final ImList<Drink> drinkLs;

    Audit() {
        this.drinkLs = new ImList<Drink>();
    }

    Audit(Drink drink, ImList<Drink> drinkLs) {
        this.drinkLs = drinkLs.add(drink);
    }

    public Audit add(Drink drink) {
        return new Audit(drink, this.drinkLs);
    }

    public String toString() {
        ImList<String> grades = new ImList<String>().add("A").add("B").add("C").add("D");
        ImList<Integer> number = new ImList<Integer>().add(0).add(0).add(0).add(0);

        for (Drink drink : drinkLs) {
            String grade = drink.value().grade();

            for (int i = 0; i < grades.size(); i++) {
                if (grades.get(i) == grade) {
                    number = number.set(i, number.get(i) + 1);
                }
            }
        }

        String gradeNum = "[";
        for (int i = 0; i < grades.size(); i++) {
            gradeNum += "(" + grades.get(i) + ", " + number.get(i) + ")";
            if (i < grades.size() - 1) {
                gradeNum += ", ";
            }
        }
        gradeNum += "]";

        return gradeNum;
    }
}



