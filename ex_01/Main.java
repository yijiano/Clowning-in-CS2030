import java.util.List;

private static final double EPSILON = 1E-15; // declare EPSILON as a constant

Circle createUnitCircle(Point p, Point q) {
    Point m = p.midPoint(q);
    double theta = p.angleTo(q);
    double mc = Math.sqrt(1.0 - Math.pow(p.distanceTo(m), 2.0));
    Point c = m.moveTo(theta + Math.PI / 2, mc);
    return new Circle(c, 1.0);
}

int findCoverage(Circle c, List<Point> points) {
    return points.stream()
        .map(point -> c.contains(point) ? 1 : 0)
        .reduce(0, (x, y) -> x + y);
}

int findMaxDiscCoverage(List<Point> points) {
    return points.stream()
        .flatMap(p -> points.stream()
                .filter(q -> p.distanceTo(q) < 2.0 + EPSILON && p.distanceTo(q) > 0)
                .map(q -> findCoverage(createUnitCircle(p, q), points)))
        .reduce(0, (x, y) -> Math.max(x, y));
}

void main() {}
