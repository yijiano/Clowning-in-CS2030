# SOLID Acronym for OOP
## S - Single-responsibility Principle
## O - Open-closed Principle
## L - Liskov Substitution Principle
## I - Interface Segregation Principle
## D - Dependency Inversion Principle

# Qn1a)
Only ii) and iii) are allowed. Look at the compile-time.

i)   Cannot since `s` is of `Shape` type and there is no `scale` method
ii)  Can since 'k' is of 'Scalable' type and there is a 'scale' method
iii) [Same thing applies]
iv)  [Same thing applies]

Java doesn't allow these statements to ensure type safety and to maintain the
contract defined by each interface.

# Qn1b)
i) 
- NOTE!! need to check the return type of scale!!
- `c.scale(0.5)` returns a `Circle`
- will compile since Circle implements both interfaces.

ii) 
- unlike part i), there `k.scale(0.5)` returns `Scalable` not `Circle`
- will not compile since k is of type Scalable which does not have a getArea
method.

# Qn1c)
Prob can, but typically you should maintain `Integration Segregation Principle (ISP)` so
that whatever class you have does not implement more than it should.

# Qn2
- It violates the Single Responsibility Principle (your class cannot "play god"). The Shape class is trying to
represent both circles and rectangles.
- It uses string comparisons to determine behaviour, which is error-prone and not
type-safe
- It's not easily extensible (OCP).
- It does not leverage polymorphism.

# Qn3 Stuff
## Example
```java
class EvenPredicate implements IntPredicate {
    public boolean test(int value) {
        return value % 2 == 0;
    }
}
```

## as a lambda expression
```java
IntPredicate and(IntPredicate p1, IntPredicate p2) {
    return x -> p1.test(x) && p2.test(x);
}
```

## as an implementation of an anonymous inner class
```java
IntPredicate and(IntPredicate p1, IntPredicate p2) {
    return new IntPredicate() {
        public boolean test(int x) {
            return p1.test(x) && p2.test(x);
        }
    };
}
```

## as an implementation of a concrete class
```java
class AndPredicate implements IntPredicate {
    private final IntPredicate p1;
    private final IntPredicate p2;

    AndPredicate(IntPredicate p1, IntPredicate p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean test(int x) {
        return p1.test(x) && p2.test(x);
    }
}

IntPredicate and(IntPredicate p1, IntPredicate p2) {
    return new AndPredicate(p1, p2);
}
```
