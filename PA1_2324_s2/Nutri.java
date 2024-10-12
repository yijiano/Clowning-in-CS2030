class Nutri {
    private final ImList<Pair<String, Integer>> ls;
    private static final int fatA = 1680;
    private static final int fatB = 2880;
    private static final int fatC = 6720;
    private static final int sugarA = 2400;
    private static final int sugarB = 12000;
    private static final int sugarC = 24000;
 
    Nutri(String nut, int ml) {
        this.ls = new ImList<Pair<String, Integer>>().add(new Pair<String, Integer>(nut, ml));
    }

    Nutri(ImList<Pair<String, Integer>> ls) {
        this.ls = ls;
    }

    public String grade() {
        int fat = 0;
        int sugar = 0;
        String fatGrade = "D";
        String sugarGrade = "D";

        for (Pair<String, Integer> nutriPair : ls) {
            if (nutriPair.first().equals("fat")) {
                fat += nutriPair.second();
            } else if (nutriPair.first().equals("sugar")) {
                sugar += nutriPair.second();
            }
        }

        if (fat <= fatA) {
            fatGrade = "A";
        } else if (fat <= fatB) {
            fatGrade = "B";
        } else if (fat <= fatC) {
            fatGrade = "C";
        }

        if (sugar <= sugarA) {
            sugarGrade = "A";
        } else if (sugar <= sugarB) {
            sugarGrade = "B";
        } else if (sugar <= sugarC) {
            sugarGrade = "C";
        }

        int compGrade = sugarGrade.compareTo(fatGrade);
        if (compGrade == 0) {
            return sugarGrade;
        } else if (compGrade == -1) {
            return fatGrade;
        } else {
            return sugarGrade;
        }
    }

    public Nutri update(String nutri, int ml) {
        ImList<Pair<String, Integer>> lsCopy = this.ls;
        boolean foundNutri = false;

        for (Pair<String, Integer> nutriPair : ls) {
            if (nutriPair.first() == nutri) {
                Pair<String, Integer> newNutriPair = new Pair<String, Integer>(
                                                            nutri, 
                                                            nutriPair.second() + ml
                );
                lsCopy = lsCopy.set(lsCopy.indexOf(nutriPair), newNutriPair);
                foundNutri = true;
            }
        }

        if (foundNutri == false) {
            lsCopy = lsCopy.add(new Pair<String, Integer>(nutri, ml));
        }

        if (this.ls != lsCopy) {
            return new Nutri(lsCopy.sort(new LsComp()));
        } else {
            return this;
        }
    }

    public String toString() {
        String str = "[";
        for (int i = 0; i < ls.size(); i++) {
            Pair<String, Integer> nutriPair = ls.get(i);
            str += "(" + nutriPair.first() + ", " + nutriPair.second() + ")";
            if (i < ls.size() - 1) {
                str += ", ";
            }
        }

        str += "]";
        return str;
    }
}
