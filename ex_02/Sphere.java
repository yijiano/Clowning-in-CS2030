public class Sphere implements Shape3D {
    private final double radius;
    private static final double FOUR_THIRDS = 4.0 / 3.0;

    Sphere(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double volume() {
        return FOUR_THIRDS * Math.PI * this.radius * this.radius * this.radius;
    }
   
    @Override
    public String toString() {
        return "sphere [" + String.format("%.2f", this.radius) + "]";
    }
}
