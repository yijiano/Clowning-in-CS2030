import java.util.Optional;
import java.util.stream.Stream;
import java.util.function.Function;

class Num extends AbstractNum<Integer> {
    
    // This constructor allows for -ve values of opt
    private Num(Optional<Integer> num) {
        super(num);
    }

    // This constructor allows for -ve values of opt
    private Num(AbstractNum<Integer> num) {
        super(num.opt);
    }

    // This method DOES NOT allow for -ve values of opt
    static Num of(int i) {
        return valid.test(i) 
            ? new Num(new AbstractNum<Integer>(i))
            : new Num(Optional.empty());
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    static Num one() {
        return Num.zero().succ();
    }

    private boolean isValidNum() {
        return !super.opt.stream()
            .map(x -> valid.test(x))
            .reduce(false, (x, y) -> x.equals(y));
    }

    Num succ() {
        return new Num(super.opt.map(x -> s.apply(x)));
    }

    Num add(Num num) {
        return new Num(super.opt.flatMap(x ->
                    num.opt.map(y -> Stream.iterate(this, n -> n.succ())
                        .skip(y)
                        .findFirst())
                    .flatMap(n -> n)
                    .flatMap(n -> n.opt)));
    }

    Num mul(Num num) {
        return new Num(super.opt.flatMap(x ->
                    num.opt.map(y -> Stream.iterate(Num.zero(), n -> n.add(this))
                        .skip(y)
                        .findFirst())
                    .flatMap(n -> n)
                    .flatMap(n -> n.opt)));
    }

    Num sub(Num num) {
        Num negativeNum = new Num(num.opt.map(x -> n.apply(x)));
        Num result = negativeNum.add(this);

        return result.isValidNum()
            ? result
            : new Num(Optional.empty());
    }   
}
