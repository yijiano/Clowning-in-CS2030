import java.util.Optional;
import java.util.stream.Stream;

class Fraction extends AbstractNum<Frac> {
    private Fraction(Optional<Frac> frac) {
        super(frac);
    }

    static Fraction of(int n, int d) {
        Num numerator = Num.of(n);
        Num denominator = Num.of(d);

        // Any -ve Num is equal to an invalid Num
        boolean isNumeratorValid = !numerator
            .equals(Num.zero().sub(Num.one()));

        boolean isDenominatorValid = !denominator.sub(Num.one())
            .equals(Num.zero().sub(Num.one()));

        return isNumeratorValid && isDenominatorValid
            ? new Fraction(Optional.of(Frac.of(numerator, denominator)))
            : new Fraction(Optional.empty());
    }
    
    Fraction add(Fraction other) {
        return new Fraction(this.opt.flatMap(frac1 ->
            other.opt.map(frac2 -> {
                Num a = frac1.t();
                Num b = frac1.u();
                Num c = frac2.t();
                Num d = frac2.u();

                Num ad = a.mul(d);
                Num bc = b.mul(c);
                Num numerator = ad.add(bc);
                Num denominator = b.mul(d);

                return Frac.of(numerator, denominator);
            })));
    }

    Fraction sub(Fraction other) {
        return new Fraction(this.opt.flatMap(frac1 ->
            other.opt.flatMap(frac2 -> {
                Num a = frac1.t();
                Num b = frac1.u();
                Num c = frac2.t();
                Num d = frac2.u();

                Num ad = a.mul(d);
                Num bc = b.mul(c);
                Num numerator = ad.sub(bc);
                Num denominator = b.mul(d);

                return numerator.equals(Num.zero().sub(Num.one()))
                    ? Optional.empty()
                    : Optional.of(Frac.of(numerator, denominator));
            })));
    }

    Fraction mul(Fraction other) {
        return new Fraction(this.opt.flatMap(frac1 ->
            other.opt.map(frac2 -> {
                Num a = frac1.t();
                Num b = frac1.u();
                Num c = frac2.t();
                Num d = frac2.u();

                Num numerator = a.mul(c);
                Num denominator = b.mul(d);

                return Frac.of(numerator, denominator);
            })));
    }
}
