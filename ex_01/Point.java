class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point p) {
        double dX = this.x - p.x;
        double dY = this.y - p.y;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Point midPoint(Point p) {
        double midX = (this.x + p.x) / 2.0;
        double midY = (this.y + p.y) / 2.0;
        return new Point(midX, midY);
    }

    public double angleTo(Point p) {
        double dX = p.x - this.x;
        double dY = p.y - this.y;
        return Math.atan2(dY, dX);
    }

    public Point moveTo(double theta, double d) {
        return new Point(x + d * Math.cos(theta), y + d * Math.sin(theta));
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }
}
