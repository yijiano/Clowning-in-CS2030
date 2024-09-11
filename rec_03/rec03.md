# Qn1a)
Only ii) and iii) are allowed.

Java doesn't allow these statements to ensure type safety and to maintain the
contract defined by each interface.

# Qn1b)
i) will compile since Circle implements both interfaces.
ii) will not compile since k is of type Scalable which does not have a getArea
method.

# Qn1c)
Prob can

# Qn2
- It violates the Single Responsibility Principle. The Shape class is trying to
  represent both circles and rectangles.
- It uses string comparisons to determine behaviour, which is error-prone and not
  type-safe
- It's not easily extensible.
- It does not leverage polymorphism.
