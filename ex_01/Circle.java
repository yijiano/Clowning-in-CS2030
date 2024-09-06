class Circle {
    private static final double EPSILON = 1E-15; // declare EPSILON as a constant
    private final Point centre;
    private final double radius;

    public Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius; 
    }

    public boolean contains(Point p) {
        return this.centre.distanceTo(p) < this.radius + EPSILON;
    }

    @Override
    public String toString() {
        return "circle of radius "
            + this.radius
            + " centred at "
            + this.centre;
    }
}
