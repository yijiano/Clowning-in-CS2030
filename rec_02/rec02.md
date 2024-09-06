# Q1a) 
Line 13 `p.x = p.x + 1;

# Q1b) 
Line 13 `p.x = p.x + 1;`
Line 20 `return new P(p.x + 1);`

These lines are attempting to directly access the private field x of class P from outside the class. This violates the abstraction barrier principle, which states that the internal implementation details of a class should be hidden from external classes. The private modifier is used to enforce this barrier, allowing access to x only within class P itself.

# Q2a)
No.
These methods are private (with the 'private' modifier) and are used internally within the Point class. They are not exposed to external classes (not available to clients), so they don't violate the Tell-Don't-Ask principle in terms of public interface design.

# Q2b)

```java
class Pair<T, U> {
    private final T t;
    private final U u;
    
    Pair (T t, U u) {
        this.t = t;
        this.u = u;
    }
}
```

Yes.
The use of t() and u() methods suggests that they are getter methods that return the internal values of the Pair object. This would violate the Tell-Don't-Ask principle, as it allows external classes to ask for internal data rather than telling the object what to do with its data.

**EDIT:** Apparently its not a violation because that is the purpose of the `Pair` class.

# Q3)
My first attempt:

```java
boolean isOverlap(Circle other) {
    
    if (!this.centre.isPresent() || !other.centre.isPresent()) {
        return false;
    }

    double distance = this.centre.get().distanceTo(other.centree.get());

    return distance < (this.radius + other.radius);
}
```
Prof Henry:

```java
boolean isOverlap(Circle other) {
    
    //note!! if you .map an optional (`centre` in this case), you get an optional<T>!!
    // flatMap is used instead of map or else you have an optional<optional<boolean>>!

    return this.centre
        .flatMap(c ->
            other.centre
                .map(k -> k.distanceTo(c) < this.radius + other.radius)
            )
        .orElse(false);
}
```
