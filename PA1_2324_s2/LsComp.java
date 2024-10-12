import java.util.Comparator;

class LsComp implements Comparator<Pair<String, Integer>> {
    public int compare(Pair<String, Integer> pairNutri, Pair<String, Integer> pairTwoNutri) {
        int compareString = pairNutri.first().compareTo(pairTwoNutri.first());
        int compareMl = pairNutri.second().compareTo(pairTwoNutri.second());

        if (compareString != 0) {
            return compareString;
        } else {
            return compareMl;
        }
    }
}
